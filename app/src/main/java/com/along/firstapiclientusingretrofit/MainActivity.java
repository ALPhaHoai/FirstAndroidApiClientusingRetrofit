package com.along.firstapiclientusingretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


import com.along.firstapiclientusingretrofit.adapter.UserAdapter;
import com.along.firstapiclientusingretrofit.api.model.SingleUserData;
import com.along.firstapiclientusingretrofit.api.model.MutilUserData;
import com.along.firstapiclientusingretrofit.api.service.Client;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pagination_list)
    RecyclerView mResultList;
    private static final String TAG = "Retrofit";

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    Client client = retrofit.create(Client.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getUsers();
    }

    public void getUsers() {
        Call<MutilUserData> call = client.getUsers();

        call.enqueue(new Callback<MutilUserData>() {
            @Override
            public void onResponse(Call<MutilUserData> call, Response<MutilUserData> response) {
                if (response.isSuccessful()) {
                    mResultList.setAdapter(new UserAdapter(MainActivity.this, response.body().getData()));
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MutilUserData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getUser(String user) {
        Call<SingleUserData> call = client.getUser(user);

        call.enqueue(new Callback<SingleUserData>() {
            @Override
            public void onResponse(Call<SingleUserData> call, Response<SingleUserData> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.body().getData().getFirstName(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SingleUserData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
