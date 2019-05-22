package com.ameba.harness.swipe.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by me on 8/1/17.
 */

public class UserResponse {

    @SerializedName("results") private List<DataLatLongdetails> userList;

    public List<DataLatLongdetails> getPeopleList () { return userList;}

    public void setPeopleList(List<DataLatLongdetails> mUserList) { this.userList = mUserList ;}

}
