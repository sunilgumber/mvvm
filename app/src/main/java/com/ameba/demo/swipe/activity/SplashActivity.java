package com.ameba.demo.swipe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.constants.Constants;
import com.ameba.demo.swipe.util.GpsUtils;

public class SplashActivity extends Activity {
    private boolean isGPS;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SwitchMainActivity();
    }

    public void SwitchMainActivity() {
        //permission check ofr sdk>23
        if(!checkLocationPermission())
            return;
        AutomaticTurnGpsOn();
    }

    private void AutomaticTurnGpsOn() {
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
                GotoMainActivity();
            }
        });
    }

    private void GotoMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }


    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        AutomaticTurnGpsOn();
                    }

                } else {
                    finish();
                }
                return;
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.GPS_REQUEST) {
                isGPS = true;
                GotoMainActivity();
            }
        }
        else
            finish();
    }
}
