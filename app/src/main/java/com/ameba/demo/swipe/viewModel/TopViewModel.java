package com.ameba.demo.swipe.viewModel;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.ameba.demo.swipe.view.listener.LocationUpdate.SmartLocationUpdate;
import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.util.Constants;
import com.ameba.demo.swipe.view.helper.MapMarkers;
import com.ameba.demo.swipe.model.data.RetrofitHelper;
import com.ameba.demo.swipe.model.entity.DataLatLongdetails;
import com.ameba.demo.swipe.model.entity.Movie;
import com.ameba.demo.swipe.util.CustomLog;
import com.ameba.demo.swipe.util.GpsUtils;
import com.ameba.demo.swipe.util.InfoWindowData;
import com.ameba.demo.swipe.view.activity.MainActivity;
import com.ameba.demo.swipe.view.fragment.TopFragment;
import com.ameba.demo.swipe.view.listener.CompletedListener;
import com.ameba.demo.swipe.view.listener.LocationUpdate.LocationUpdateListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by sunil on 5/21/19.
 */

public class TopViewModel {
    public ObservableField<String> toolbar;
    public ObservableField<String> eventname;
    public ObservableField<String> eventdate;
    public ObservableField<String> eventadress;
    public ObservableField<Integer> layoutdetail;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private boolean isGPS;
    private final TopFragment context;
    private Subscriber<Movie> subscriber;
    MainActivity mainActivity;
    SmartLocationUpdate smartLocation;

    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    ArrayList<DataLatLongdetails.dataEntity> LatLongdetails = new ArrayList<>();
    private CompletedListener completedListener;


    public TopViewModel(TopFragment context, CompletedListener completedListener) {
        this.context = context;
        mainActivity = (MainActivity) context.getActivity();
        this.completedListener = completedListener;
        initdata();
        // getSplashData();
    }

    private void initdata() {
        toolbar = new ObservableField<>();
        eventadress = new ObservableField<>();
        eventdate = new ObservableField<>();
        eventname = new ObservableField<>();
        layoutdetail = new ObservableField<>();
        layoutdetail.set(View.GONE);
    }

    public void onBackClick() {
        mainActivity.movetocenter();
    }


    //unused
    public void CallEventsDataAPI() {
        subscriber = new Subscriber<Movie>() {
            @Override
            public void onCompleted() {
                Log.d("[MainViewModel]", "onCompleted");
                completedListener.onCompleted();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {

            }
        };
        RetrofitHelper.getInstance().getMovies(subscriber, 0, 20);
    }

    public void startLocation(LocationUpdateListener listener) {
        smartLocation=new SmartLocationUpdate(context.getActivity(),listener);
        smartLocation.ContinousUpdateLoc();
    }

    public void LoadMapOnValidCordinates(Location location) {
        if (GpsUtils.isvalidcoordinates(location)&&GpsUtils.isvaliddistance(location)){
            loadmap(location);
        }

    }


    private void loadjson() {
        DataLatLongdetails dataLatLongdetails=new Gson().fromJson(Constants.loadJSONFromAsset(context.getActivity(), "Location.json"),DataLatLongdetails.class);
        LatLongdetails= (ArrayList<DataLatLongdetails.dataEntity>) dataLatLongdetails.getLocation_data();
        CustomLog.debug(new Gson().toJson(LatLongdetails));
    }


    private void loadmap(Location location) {
        try {
            mainActivity.currentlatlng=new LatLng(location.getLatitude(),location.getLongitude());
            FragmentManager fm = (context.getActivity()).getSupportFragmentManager();
            if (supportMapFragment == null) {
                supportMapFragment = SupportMapFragment.newInstance();
                fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
            }
            supportMapFragment.getMapAsync((OnMapReadyCallback) context);

        }
        catch (Exception e){
            CustomLog.error(e.toString());
        }
    }


    public void stoplocation() {
        smartLocation.stoplocation();
    }
    MapMarkers mapMarkers;
    public void onMapReady(GoogleMap googleMap, TopFragment topFragment) {
        googleMap.setOnMarkerClickListener(context);
        loadjson();
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(context.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mainActivity.currentlatlng, 15);
        map.animateCamera(cameraUpdate);
        mapMarkers=new MapMarkers(googleMap,topFragment,LatLongdetails,mainActivity.currentlatlng);
        mapMarkers.afterMapLoad();;
    }

    public void showDetailSlide(Marker marker) {;
        InfoWindowData infoWindowData=new Gson().fromJson(new Gson().toJson(marker.getTag()),InfoWindowData.class);
        layoutdetail.set(View.VISIBLE);
        eventname.set(LatLongdetails.get(infoWindowData.getposition()).getTitle());
        eventadress.set(LatLongdetails.get(infoWindowData.getposition()).getAddressLine2());
        eventdate.set(LatLongdetails.get(infoWindowData.getposition()).getDate());
    }
}
