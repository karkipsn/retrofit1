package com.journaldev.retrofitintro.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by colors2web on 3/20/2018.
 */

public class LoginUser {
    final String email;
    final String password;

   // @SerializedName("token")
    public String token;

//To send or serialize the data you have to pass through the constructor and passing the parameters
    // by creating new objects in Activity class be it json object or json array

    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
