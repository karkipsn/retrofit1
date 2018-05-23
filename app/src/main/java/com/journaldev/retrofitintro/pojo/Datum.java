package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by colors2web on 3/23/2018.
 */

public class Datum {
    @SerializedName("id")
    public Integer id;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("avatar")
    public String avatar;
}
