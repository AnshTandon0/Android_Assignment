package com.example.assignmentandroid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country extends BaseCountry {

    @SerializedName("flag")
    private String flag;

    @SerializedName("languages")
    private List<Language> languages;

    public Country(String name, String capital, String region, String subRegion, int population, List<String> borders, String flag, List<Language> languages) {
        super(name, capital, region, subRegion, population, borders);
        this.flag = flag;
        this.languages = languages;
    }

    public Country()
    {

    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
