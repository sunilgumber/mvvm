package com.ameba.demo.swipe.network;


import com.ameba.demo.swipe.pojo.DataLatLongdetails;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Ahmad Shubita on 8/1/17.
 */

public interface UsersService {

    @GET
    Observable<DataLatLongdetails> fetchUsers(@Url String url);
}
