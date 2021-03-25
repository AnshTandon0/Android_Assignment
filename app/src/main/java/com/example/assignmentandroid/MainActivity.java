package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectAndGetApi();
    }

    public void connectAndGetApi()
    {
        if ( retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(" https://restcountries.eu/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        CountryApiService countryApiService = retrofit.create(CountryApiService.class);
        Call<List<Country>> call = countryApiService.getByRegion("asia");
        call.enqueue( new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    List<Country> countries = response.body();
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    CountryAdapter countryAdapter = new CountryAdapter(getApplicationContext(), countries , MainActivity.this);
                    recyclerView.setAdapter(countryAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Log.d("", "Number of countries received: " );
            }
        });
    }
}