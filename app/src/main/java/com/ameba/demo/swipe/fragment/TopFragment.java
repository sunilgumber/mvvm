package com.ameba.demo.swipe.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ameba.demo.swipe.LocationUpdate.SmartLocationUpdate;
import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.activity.MainActivity;
import com.ameba.demo.swipe.backpress.BaseBackPressedListener;
import com.ameba.demo.swipe.constants.Constants;
import com.ameba.demo.swipe.inteface.LocationUpdateListener;
import com.ameba.demo.swipe.map.CustomInfoWindowGoogleMap;
import com.ameba.demo.swipe.map.MapMarkers;
import com.ameba.demo.swipe.pojo.DataLatLongdetails;
import com.ameba.demo.swipe.util.CustomLog;
import com.ameba.demo.swipe.util.GpsUtils;
import com.ameba.demo.swipe.util.InfoWindowData;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Fragment to manage the top page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class TopFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    @BindView(R.id.rlback)
    RelativeLayout rlback;
    Unbinder unbinder;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.fragmenttop)
    RelativeLayout fragmenttop;
    private Context mContext;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    ArrayList<DataLatLongdetails.dataEntity> LatLongdetails = new ArrayList<>();
    MainActivity mainActivity;
    SmartLocationUpdate smartLocation;
    LocationUpdateListener listener=new LocationUpdateListener() {
        @Override
        public void OneShot(Location location) {
            if (GpsUtils.isvalidcoordinates(location)) loadmap(location);
        }

        @Override
        public void Continous(Location location) {
            if (GpsUtils.isvalidcoordinates(location)) loadmap(location);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        smartLocation=new SmartLocationUpdate(mContext,listener);
        smartLocation.ContinousUpdateLoc();
        View fragmentView = inflater.inflate(R.layout.fragment_top, container, false);
        mainActivity = (MainActivity) getActivity();
        mainActivity.fragmentValue = Constants.FragMaps;
        mainActivity.setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        unbinder = ButterKnife.bind(this, fragmentView);
        loadjson();
        return fragmentView;
    }

    private void loadjson() {
        DataLatLongdetails dataLatLongdetails=new Gson().fromJson(Constants.loadJSONFromAsset(mContext, "Location.json"),DataLatLongdetails.class);
        LatLongdetails= (ArrayList<DataLatLongdetails.dataEntity>) dataLatLongdetails.getLocation_data();
        CustomLog.debug(new Gson().toJson(LatLongdetails));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        txtName.setText(Constants.FragMaps);
    }

    private void loadmap(Location location) {
        try {
            mainActivity.currentlatlng=new LatLng(location.getLatitude(),location.getLongitude());
            FragmentManager fm = getActivity().getSupportFragmentManager();
            if (supportMapFragment == null) {
                supportMapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
            }
            supportMapFragment.getMapAsync(TopFragment.this);

        }
        catch (Exception e){
        CustomLog.error(e.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mainActivity.currentlatlng, 15);
        map.animateCamera(cameraUpdate);
        new MapMarkers(googleMap,this,LatLongdetails,mainActivity.currentlatlng).afterMapLoad();

    }



    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        smartLocation.stoplocation();
    }

    @OnClick(R.id.rlback)
    public void onClick() {
        mainActivity.movetocenter();
    }

}