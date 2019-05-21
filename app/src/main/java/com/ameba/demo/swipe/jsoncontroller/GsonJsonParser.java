package com.ameba.demo.swipe.jsoncontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonJsonParser {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'kk:mm:ss").create();

    public static <T> T parseServerResponse(final String json, final Class<T> clazz) {     //@param json  @param clazz  @return
        return gson.fromJson(json, clazz);
    }
}
