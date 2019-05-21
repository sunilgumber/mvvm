package com.ameba.demo.swipe.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ameba on 15/5/19.
 */

public class DataLatLongdetails {

    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private String status;
    @SerializedName("location_data")
    private List<dataEntity> location_data;

    public DataLatLongdetails() {
    }

    public static class dataEntity {
        @Expose
        @SerializedName("city")
        private String city;
        @Expose
        @SerializedName("addressLine2")
        private String addressLine2;
        @Expose
        @SerializedName("geoLongitude")
        private String geoLongitude;
        @Expose
        @SerializedName("geoLatitude")
        private String geoLatitude;
        @Expose
        @SerializedName("title")
        private String title;
        @Expose
        @SerializedName("date")
        private String date;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getGeoLongitude() {
            return geoLongitude;
        }

        public void setGeoLongitude(String geoLongitude) {
            this.geoLongitude = geoLongitude;
        }

        public String getGeoLatitude() {
            return geoLatitude;
        }

        public void setGeoLatitude(String geoLatitude) {
            this.geoLatitude = geoLatitude;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    public List<dataEntity> getLocation_data() {
        return location_data;
    }

    public void setLocation_data(List<dataEntity> location_data) {
        this.location_data = location_data;
    }

}
