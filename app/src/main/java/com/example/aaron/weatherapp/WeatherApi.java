package com.example.aaron.weatherapp;



import com.example.aaron.weatherapp.WeatherData.WeatherData;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Url;


/**
 * Created by aaron on 12/29/15.
 */
public interface WeatherApi {
    @GET("/data/2.5/weather?q={city name},{country code}&APPID=e8060058ae43938791ea026093e2c8da")
    Call<WeatherData> getCurrentCityWeather(@Query("q") String cityName,
                                            @Query("q")String countryCode);
}

