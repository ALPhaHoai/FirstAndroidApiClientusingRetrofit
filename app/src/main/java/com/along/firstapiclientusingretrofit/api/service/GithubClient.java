package com.along.firstapiclientusingretrofit.api.service;

import com.along.firstapiclientusingretrofit.api.model.GithubRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubClient {

    @GET("/user/{user}/repos")
    retrofit2.Call<List<GithubRepo>> reposForUser(@Path("user") String user);
}
