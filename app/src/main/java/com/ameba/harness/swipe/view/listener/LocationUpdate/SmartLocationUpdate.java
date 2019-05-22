package com.ameba.harness.swipe.view.listener.LocationUpdate;

import android.content.Context;
import android.location.Location;

import com.ameba.harness.swipe.view.activity.MainActivity;
import com.ameba.harness.swipe.util.CustomLog;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationAccuracy;
import io.nlopez.smartlocation.location.config.LocationParams;

/**
 * Created by ameba on 16/5/19.
 */

public class SmartLocationUpdate {
    private final LocationUpdateListener listener;
    Context context;
    MainActivity mainActivity;
    long mLocTrackingInterval = 1000 * 5; // 5 sec
    float trackingDistance = 0;
    LocationAccuracy trackingAccuracy = LocationAccuracy.MEDIUM;
    public SmartLocationUpdate(Context context, LocationUpdateListener locationUpdateListener) {
        this.listener = locationUpdateListener;
        this.context = context;
    }

    public void ContinousUpdateLoc() {
        LocationParams.Builder builder = new LocationParams.Builder()
                .setAccuracy(trackingAccuracy)
                .setDistance(trackingDistance)
                .setInterval(mLocTrackingInterval);
        SmartLocation.with(context)
                .location()
                .continuous()
                .config(builder.build())
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        if(location==null)
                        return;
                        listener.Continous(location);
                        CustomLog.error("location  " + String.valueOf(location.getLatitude() + location.getLongitude()));
                    }
                });
    }

    public void OneFixLoc(){
        SmartLocation.with(context).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        listener.OneShot(location);
                        CustomLog.error("OneFixLoc  " + String.valueOf(location.getLatitude() + location.getLongitude()));
                    }
                });
    }

    public void stoplocation(){
        SmartLocation.with(context).location().stop();
    }
}
