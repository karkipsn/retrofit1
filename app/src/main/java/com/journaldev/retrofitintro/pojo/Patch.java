package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by colors2web on 3/26/2018.
 */

public class Patch {
    @SerializedName("name")
    public String name;
    @SerializedName("updatedAt")
    public String updatedAt;
    @SerializedName("job")
    public String job;

    public Patch(String name, String job) {
        this.name = name;
        this.job = job;

    }

    public Patch() {

    }
}
