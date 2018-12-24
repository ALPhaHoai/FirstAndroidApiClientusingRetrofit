package com.along.firstapiclientusingretrofit.api.service;
import com.along.firstapiclientusingretrofit.api.model.SingleUserData;
import com.along.firstapiclientusingretrofit.api.model.MutilUserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Client {

    @GET("users/{user}")
    Call<SingleUserData> getUser(@Path("user") String user);

    @GET("/api/users")
    Call<MutilUserData> getUsers();
}
