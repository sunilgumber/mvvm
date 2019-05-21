package com.ameba.demo.swipe.util;

/**
 * Created by ameba on 15/5/19.
 */

public class DataLatLongdetails {
    private String title;
    private String GeoLatitude;
    private String GeoLongitude;
    private String AddressLine2;
    private String City;

    public DataLatLongdetails(String title,String geoLatitude, String geoLongitude, String addressLine2, String city) {
        this.title = title;
        GeoLatitude = geoLatitude;
        GeoLongitude = geoLongitude;
        AddressLine2 = addressLine2;
        City = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGeoLatitude() {
        return GeoLatitude;
    }

    public void setGeoLatitude(String geoLatitude) {
        GeoLatitude = geoLatitude;
    }

    public String getGeoLongitude() {
        return GeoLongitude;
    }

    public void setGeoLongitude(String geoLongitude) {
        GeoLongitude = geoLongitude;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }
}
