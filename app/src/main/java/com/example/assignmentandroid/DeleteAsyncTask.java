package com.example.assignmentandroid;

import android.os.AsyncTask;

public class DeleteAsyncTask extends AsyncTask<Void,Void,Void> {

    private CountryDao countryDao;

    public DeleteAsyncTask( CountryDao countryDao)
    {
        this.countryDao = countryDao;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        countryDao.delete();
        return null;
    }
}
