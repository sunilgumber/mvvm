package com.ameba.demo.swipe.view.helper;

import android.location.Location;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ameba.demo.swipe.R;
import com.ameba.demo.swipe.view.fragment.TopFragment;
import com.ameba.demo.swipe.model.entity.DataLatLongdetails;
import com.ameba.demo.swipe.util.InfoWindowData;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by ameba on 20/5/19.
 */

public class MapMarkers {
    private GoogleMap map;
    private TopFragment context;
    ArrayList<DataLatLongdetails.dataEntity> LatLongdetails = new ArrayList<>();

    private MarkerOptions currentPositionMarker = null;
    private Marker currentLocationMarker;
    LatLng currentlocation;

    public MapMarkers(GoogleMap map, TopFragment context, ArrayList<DataLatLongdetails.dataEntity> latLongdetails, LatLng currentlocation) {
        this.map = map;
        this.context = context;
        LatLongdetails = latLongdetails;
        this.currentlocation = currentlocation;
    }

    public void afterMapLoad() {
        map.clear();
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = context.getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) context.getActivity().findViewById(R.id.map_container), false);
                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());
                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());
                map.setOnInfoWindowClickListener(context);
                return infoWindow;
            }
        });
        LatLng latLng = null;
        for (int i = 0; i < LatLongdetails.size(); i++) {
            String lat = LatLongdetails.get(i).getGeoLatitude();
            String lng = LatLongdetails.get(i).getGeoLongitude();

            if (lat != null && lng != null && !lat.isEmpty() && !lng.isEmpty()) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.avatar));
                latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                markerOptions.position(latLng);
                markerOptions.title(LatLongdetails.get(i).getTitle());
                if (currentlocation != null) {
                    Location locations = new Location("");
                    locations.setLatitude(Double.parseDouble(lat));
                    locations.setLongitude(Double.parseDouble(lng));

                    InfoWindowData info = new InfoWindowData();
                    if (!LatLongdetails.get(i).getAddressLine2().equals(""))
                    info.setAddress(LatLongdetails.get(i).getAddressLine2());
                    else
                    info.setAddress(LatLongdetails.get(i).getAddressLine2());
                    info.setDistance(LatLongdetails.get(i).getDate());
                    info.setTitle(LatLongdetails.get(i).getTitle());
                    info.setposition(i);

                    CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(context.getActivity());
                    map.setInfoWindowAdapter(customInfoWindow);

                    currentLocationMarker = map.addMarker(markerOptions);
                    currentLocationMarker.setTag(info);
                }
            }
        }

        map.setOnInfoWindowClickListener(context);
    }
}
