package com.example.assignmentandroid;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert
    void Insert(CountryEntity countryEntity);

    @Query( "DELETE FROM Country")
    void delete();

    @Query("SELECT * FROM Country")
    List<CountryEntity> getAll();

}
