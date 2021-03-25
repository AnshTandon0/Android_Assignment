package com.example.assignmentandroid;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CountryEntity.class},version = 1,exportSchema = false)
public abstract class CountryDatabase extends RoomDatabase {

    private static CountryDatabase db;
    public abstract CountryDao countryDao();

    public static CountryDatabase getInstance(Context context)
    {
        if (db==null)
        {
            db = CountryDatabase.buildDatabase(context);
        }
        return db;
    }

    private static CountryDatabase buildDatabase( Context context )
    {
        return Room.databaseBuilder(context,CountryDatabase.class,"database")
                //.allowMainThreadQueries()
                .build();
    }
}
