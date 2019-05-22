package com.ameba.demo.swipe.view.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.util.InfoWindowData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ameba on 10/9/18.
 */

public class CustomInfoWindowGoogleMap implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public CustomInfoWindowGoogleMap(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custom_info_contents, null);
        TextView title = view.findViewById(R.id.title);
        TextView address = view.findViewById(R.id.address);
        TextView distance = view.findViewById(R.id.snippet);
        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();
        title.setText(infoWindowData.getTitle());
        address.setText(infoWindowData.getAddress());
        distance.setText(infoWindowData.getDistance());
        return view;
    }
}