package com.along.firstapiclientusingretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.along.firstapiclientusingretrofit.api.model.GithubRepo;
import com.along.firstapiclientusingretrofit.api.service.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GithubClient githubClient = retrofit.create(GithubClient.class);

        retrofit2.Call<List<GithubRepo>> call = githubClient.reposForUser("ALPhaHoai");

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
