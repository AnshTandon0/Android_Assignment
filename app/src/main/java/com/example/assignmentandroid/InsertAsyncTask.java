package com.example.assignmentandroid;

import android.os.AsyncTask;

public class InsertAsyncTask extends AsyncTask<CountryEntity,Void,Void> {

    private CountryDao countryDao;

    public InsertAsyncTask( CountryDao countryDao)
    {
        this.countryDao = countryDao;
    }

    @Override
    protected Void doInBackground(CountryEntity... countryEntities) {
        countryDao.Insert(countryEntities[0]);
        return null;
    }
}
