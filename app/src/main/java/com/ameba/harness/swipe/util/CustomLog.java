package com.ameba.harness.swipe.util;

import android.util.Log;

/**
 * Created by ameba on 6/2/19.
 */

public class CustomLog {
    public static void error(String tag, String message) {
        Log.e(tag, message);
    }

    public static void error(String message) {
        Log.e(Constants.Tag,message);
    }

    public static void error(String tag, String message, Exception e)
    {
        Log.e(tag, message, e);
    }

    public static void error(Exception e) {

        Log.e(Constants.Tag, Log.getStackTraceString(e));
    }

    public static void debug(String message) {
        if(null!=message){
            Log.d(Constants.Tag, message);
        }
    }

    public static void info(String tag, String message) {

        Log.i(tag, message);
    }
}
