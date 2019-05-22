package com.ameba.harness.swipe.view.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ameba.harness.swipe.R;
import com.ameba.harness.swipe.databinding.FragmentTopBinding;
import com.ameba.harness.swipe.util.CustomLog;
import com.ameba.harness.swipe.view.activity.MainActivity;
import com.ameba.harness.swipe.view.listener.CompletedListener;
import com.ameba.harness.swipe.view.listener.backpress.BaseBackPressedListener;
import com.ameba.harness.swipe.util.Constants;
import com.ameba.harness.swipe.view.listener.LocationUpdate.LocationUpdateListener;
import com.ameba.harness.swipe.viewModel.TopViewModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;


/**
 * Fragment to manage the top page of the 5 pages application navigation (top, center, bottom, left, right).
 */
public class TopFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,CompletedListener,LocationUpdateListener,GoogleMap.OnMarkerClickListener {

    MainActivity mainActivity;
    FragmentTopBinding fragmentTopBinding;
    TopViewModel topViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context mContext = getActivity();
        View fragmentView = inflater.inflate(R.layout.fragment_top, container, false);
        fragmentTopBinding=FragmentTopBinding.bind(fragmentView);
        mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
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
    public void OneShot(Location location) {
        // topViewModel.checkValidCordinates(GpsUtils.isvalidcoordinates(location));
        //if (GpsUtils.isvalidcoordinates(location)) loadmap(location);
    }

    @Override
    public void Continous(Location location) {
        topViewModel.LoadMapOnValidCordinates(location);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        topViewModel.onMapReady(googleMap,TopFragment.this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        CustomLog.error(marker.getId());
    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onFailed() {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        topViewModel.showDetailSlide(marker);
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        topViewModel.stoplocation();
    }
}