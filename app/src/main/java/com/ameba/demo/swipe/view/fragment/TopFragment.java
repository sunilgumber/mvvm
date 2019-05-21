package com.ameba.demo.swipe.view.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.databinding.FragmentTopBinding;
import com.ameba.demo.swipe.view.activity.MainActivity;
import com.ameba.demo.swipe.view.listener.CompletedListener;
import com.ameba.demo.swipe.view.listener.backpress.BaseBackPressedListener;
import com.ameba.demo.swipe.constants.Constants;
import com.ameba.demo.swipe.view.listener.LocationUpdateListener;
import com.ameba.demo.swipe.viewModel.TopViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;


/**
 * Fragment to manage the top page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class TopFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,CompletedListener,LocationUpdateListener {

    private Context mContext;
    MainActivity mainActivity;
    FragmentTopBinding fragmentTopBinding;
    TopViewModel topViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        View fragmentView = inflater.inflate(R.layout.fragment_top, container, false);
        fragmentTopBinding=FragmentTopBinding.bind(fragmentView);
        mainActivity = (MainActivity) getActivity();
        mainActivity.fragmentValue = Constants.FragMaps;
        mainActivity.setOnBackPressedListener(new BaseBackPressedListener(getActivity()));
        initData();
        return fragmentView;
    }

    private void initData() {
        topViewModel = new TopViewModel(TopFragment.this,this);
        fragmentTopBinding.setViewModel(topViewModel);
      //  topViewModel.CallEventsDataAPI();
        topViewModel.startLocation(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        topViewModel.onMapReady(googleMap,TopFragment.this);

    }



    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topViewModel.stoplocation();
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public void OneShot(Location location) {
        // topViewModel.checkValidCordinates(GpsUtils.isvalidcoordinates(location));
        //if (GpsUtils.isvalidcoordinates(location)) loadmap(location);
    }

    @Override
    public void Continous(Location location) {
         topViewModel.LoadMapOnValidCordinates(location);
    }
}