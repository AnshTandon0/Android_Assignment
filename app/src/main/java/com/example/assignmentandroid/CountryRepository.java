package com.example.assignmentandroid;

import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CountryRepository {

    private CountryDao countryDao;

    public CountryRepository (Context context)
    {
        CountryDatabase countryDatabase = CountryDatabase.getInstance(context);
        countryDao = countryDatabase.countryDao();
    }

    public void Insert( CountryEntity countryEntity)
    {
        new InsertAsyncTask(countryDao).execute(countryEntity);
    }

    public void Delete ( )
    {
        new DeleteAsyncTask(countryDao).execute();
    }

    public List<CountryEntity> getAll() throws ExecutionException, InterruptedException {
        return new SelectAsyncTask(countryDao).execute().get();
    }
}
