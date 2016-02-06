package com.example.aaron.weatherapp;



import com.example.aaron.weatherapp.WeatherData.WeatherData;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;



/**
 * Created by aaron on 12/29/15.
 */
public interface WeatherApi {
    @GET("/data/2.5/weather")
    Call<WeatherData> getCurrentCityWeather(@Query("q") String cityName,
                                            @Query("APPID") String AppID);
}

