package com.example.assignmentandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryApiService {

    @GET (" rest/v2/region/{region}?fields=flag;languages;name;capital;region;subregion;population;borders")
    Call <List<Country>> getByRegion ( @Path("region") String region );
}
