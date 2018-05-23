package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;
/**
 * Created by colors2web on 3/20/2018.
 */

public class User {


    @SerializedName("name")
    public String name;
    @SerializedName("job")
    public String job;
    @SerializedName("id")
    public String id;
    @SerializedName("createdAt")
    public String createdAt;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }


}
