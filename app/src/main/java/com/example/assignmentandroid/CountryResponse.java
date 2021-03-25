package com.example.assignmentandroid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryResponse {


    @SerializedName("results")
    private List<BaseCountry> results;

    public List<BaseCountry> getResults() {
        return results;
    }

    public void setResults(List<BaseCountry> results) {
        this.results = results;
    }

    public CountryResponse(List<BaseCountry> results) {
        this.results = results;
    }
}

