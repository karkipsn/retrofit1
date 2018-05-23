package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by colors2web on 3/23/2018.
 */

public class Userput {
    @SerializedName("name")
    public String name;
    @SerializedName("updatedAt")
    public String updatedAt;
    @SerializedName("job")
    public String job;

    public Userput(String name, String job) {
        this.name = name;
        this.job = job;

}}
