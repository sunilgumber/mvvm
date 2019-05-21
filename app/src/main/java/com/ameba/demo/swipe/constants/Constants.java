package com.ameba.demo.swipe.constants;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Electrovese on 4/25/2017.
 */
public class Constants {
    public static final String SHARED_PREFERENCE_NAME = "SharedPreference";
    public static final String EMPTY = "";
    public static final String FragMaps = "Maps";
    public static final String FragCentral = "FragCentral";
    public static final String FragTop = "FragTop";
    public static final String FragBottom = "FragBottom";
    public static final String FragLeft = "FragLeft";
    public static final String FragRight = "FragRight";
    public static final int GPS_REQUEST =111 ;
    public static final String Tag="Ameba";
    public static final  String BASE_URL = "http://api.randomuser.me/";
    public  static final  String RANDOM_USER_URL = "http://api.randomuser.me/?results=10&nat=en";



    public static String loadJSONFromAsset(Context context, String jsonfile) {
        String json;
        try {
            InputStream is = context.getAssets().open(jsonfile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
