package com.ameba.harness.swipe.viewModel;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.ameba.harness.swipe.model.data.RetrofitHelper;
import com.ameba.harness.swipe.util.Constants;
import com.ameba.harness.swipe.util.GpsUtils;
import com.ameba.harness.swipe.view.activity.SplashActivity;
import com.ameba.harness.swipe.view.listener.CompletedListener;

import rx.Subscriber;


/**
 * Created by HaohaoChang on 2017/2/11.
 */
public class SplashViewModel {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private boolean isGPS;
    private final Context context;

    private CompletedListener completedListener;

    public SplashViewModel(Context context,CompletedListener completedListener) {
        this.context=context;
        this.completedListener = completedListener;
       // getSplashData();
    }


    public void SwitchMainActivity() {
        //permission check ofr sdk>23
        if(!checkLocationPermission())
            return;
        AutomaticTurnGpsOn();
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions((SplashActivity) context,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    private void AutomaticTurnGpsOn() {
        new GpsUtils(context).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
                completedListener.onCompleted();

            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.GPS_REQUEST) {
                isGPS = true;
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(context,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        AutomaticTurnGpsOn();
                    }

                } else {
                    completedListener.onFailed();
                }
                return;
            }

        }
    }

}
