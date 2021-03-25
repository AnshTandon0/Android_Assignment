package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit = null;
    private CountryRepository countryRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryRepository = new CountryRepository(this);
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
                    try {
                        if(countryRepository.getAll() == null)
                        {
                            saveToDatabase(countries);
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        if(countryRepository.getAll()!=null)
                        {
                            List<Country> countries = extractDataFromDatabase();
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            CountryAdapter countryAdapter = new CountryAdapter(getApplicationContext(), countries , MainActivity.this);
                            recyclerView.setAdapter(countryAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        }
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

                Log.d("", "Number of countries received: ");
                try {
                    if(countryRepository.getAll() != null)
                    {
                        List<Country> countries = extractDataFromDatabase();
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        CountryAdapter countryAdapter = new CountryAdapter(getApplicationContext(), countries , MainActivity.this);
                        recyclerView.setAdapter(countryAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void saveToDatabase(List<Country> countries)
    {
        for (int i=0 ; i < countries.size() ; i++)
        {
            CountryEntity countryEntity = new CountryEntity();

            countryEntity.setName(countries.get(i).getName());
            countryEntity.setCapital(countries.get(i).getCapital());
            countryEntity.setRegion(countries.get(i).getRegion());
            countryEntity.setSubRegion(countries.get(i).getSubRegion());
            countryEntity.setPopulation(countries.get(i).getPopulation());
            countryEntity.setFlag(countries.get(i).getFlag());
            countryEntity.setBorders(DataConverter.writingStringFromList(countries.get(i).getBorders()));
            countryEntity.setLanguages(DataConverter.stringFromListOfLanguages(countries.get(i).getLanguages()));

            countryRepository.Insert(countryEntity);

        }
    }

    public List<Country> extractDataFromDatabase() throws ExecutionException, InterruptedException {
        List<Country> countries = new ArrayList<>();
        CountryRepository countryRepository = new CountryRepository(this);
        List<CountryEntity> countryEntities = countryRepository.getAll();

        for ( int i = 0; i < countryEntities.size() ; i++)
        {
            Country country = new Country();

            country.setName(countryEntities.get(i).getName());
            country.setRegion(countryEntities.get(i).getRegion());
            country.setSubRegion(countryEntities.get(i).getSubRegion());
            country.setCapital(countryEntities.get(i).getCapital());
            country.setPopulation(countryEntities.get(i).getPopulation());
            country.setFlag(countryEntities.get(i).getFlag());
            country.setBorders(DataConverter.gettingListFromString(countryEntities.get(i).getBorders()));
            country.setLanguages(DataConverter.listOfLanguageFromString(countryEntities.get(i).getLanguages()));

            countries.add(country);
        }

        return countries;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return true;
    }

   public void delete (MenuItem item)
    {
        countryRepository.Delete();
        recreate();
    }
    public void refresh(MenuItem item)
    {
        recreate();
    }
}