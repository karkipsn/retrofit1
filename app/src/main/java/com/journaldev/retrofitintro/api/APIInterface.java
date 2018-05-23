package com.journaldev.retrofitintro.api;

import com.journaldev.retrofitintro.pojo.LoginUser;
import com.journaldev.retrofitintro.pojo.MultipleResource;
import com.journaldev.retrofitintro.pojo.Patch;
import com.journaldev.retrofitintro.pojo.User;
import com.journaldev.retrofitintro.pojo.UserList;
import com.journaldev.retrofitintro.pojo.Userput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by colors2web on 3/20/2018.
 */


public interface APIInterface {

    @GET("/api/unknown")
    Call<MultipleResource> doGetListResources();

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);// send in network as https://reqres.in/api/users?&page=2

    @POST("/api/login")
    Call<LoginUser> postUser(@Body LoginUser luser);

    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

    @PUT("/api/users/{id}")
    Call<Patch> doPutUser(@Path("id") String id, @Body Patch userput);

    @PATCH("/api/users/{id}")
    Call<Patch> doPatchUser(@Path("id") String id, @Body Patch userput);

    @DELETE("/api/users/{id}")
    Call<Patch>delete(@Path("id") String id);
}
//For using query
// @GET("/data/2.5/weather?")
//    Call<City> getWeather(@Query("q") String location, @Query("appid") String key);
