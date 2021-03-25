package com.example.assignmentandroid;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Country")
public class CountryEntity {

    @PrimaryKey
    @NonNull
    private String name;

    @NonNull
    private String capital;

    @NonNull
    private String region;

    @NonNull
    private String subRegion;

    @NonNull
    private String borders;

    @NonNull
    private int population;

    @NonNull
    private String flag;

    @NonNull
    private String languages;

    CountryEntity()
    {

    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getCapital() {
        return capital;
    }

    public void setCapital(@NonNull String capital) {
        this.capital = capital;
    }

    @NonNull
    public String getRegion() {
        return region;
    }

    public void setRegion(@NonNull String region) {
        this.region = region;
    }

    @NonNull
    public String getSubRegion() {
        return subRegion;
    }

    public void setSubRegion(@NonNull String subRegion) {
        this.subRegion = subRegion;
    }

    @NonNull
    public String getBorders() {
        return borders;
    }

    public void setBorders(@NonNull String borders) {
        this.borders = borders;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @NonNull
    public String getFlag() {
        return flag;
    }

    public void setFlag(@NonNull String flag) {
        this.flag = flag;
    }

    @NonNull
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(@NonNull String languages) {
        this.languages = languages;
    }
}
