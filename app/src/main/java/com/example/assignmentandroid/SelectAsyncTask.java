package com.example.assignmentandroid;

import android.os.AsyncTask;

import java.util.List;

public class SelectAsyncTask extends AsyncTask<Void , Void , List<CountryEntity>> {

    private CountryDao countryDao;

    public SelectAsyncTask(CountryDao countryDao)
    {
        this.countryDao = countryDao;
    }

    @Override
    protected List<CountryEntity> doInBackground(Void... voids) {

        if(countryDao.getAll().size() ==0)
        {
            return null;
        }
        else
        {
        return countryDao.getAll();
    }}
}
