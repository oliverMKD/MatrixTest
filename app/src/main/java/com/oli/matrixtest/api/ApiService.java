package com.oli.matrixtest.api;

import com.oli.matrixtest.klasi.Model;
import com.oli.matrixtest.klasi.OpenWeatherMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("weather?"+"appid="+ApiConstants.api_key)
    Call<OpenWeatherMap> getCurrentWeather(@Query("units") String units,@Query("lat") String lat, @Query("lon") String lon);
    @GET("forecast?"+"appid="+ApiConstants.api_key)
    Call<Model> getForecast(@Query("units") String units, @Query("lat") String lat, @Query("lon") String lon);

    @GET("weather?"+"appid="+ApiConstants.api_key)
    Call<OpenWeatherMap> SearchWeather(@Query("units") String units,@Query("q") String q);

    @GET("forecast?"+"appid="+ApiConstants.api_key)
    Call<Model> searchForecast(@Query("units") String units, @Query("q") String q);


}
