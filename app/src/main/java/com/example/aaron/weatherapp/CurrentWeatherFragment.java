package com.example.aaron.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.aaron.weatherapp.WeatherData.WeatherData;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by aaron on 1/5/16.
 */
public class CurrentWeatherFragment extends Fragment {

    private static final String WEATHER_URL = "http://api.openweathermap.org";
    private TextView tempTextView;
    private TextView conditionTextView;
    private TextView humidityTextValue;
    private TextView precipitationTextValue;
    private TextView windTextValue;
    private ImageView weatherImage;
    private double kelvin;
    private double kelvinToCelsius;
    private int celsiusToFahrenheit;
    private String cityName, countryCode;
    CurrentWeatherFragment cFrag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.current_weather, container, false);
        tempTextView = (TextView) view.findViewById(R.id.temp_text);
        weatherImage = (ImageView) view.findViewById(R.id.weather_image);
        conditionTextView = (TextView) view.findViewById(R.id.condition_text);
        humidityTextValue = (TextView) view.findViewById(R.id.humidity_value);
        precipitationTextValue = (TextView) view.findViewById(R.id.precipitation_value);
        windTextValue = (TextView) view.findViewById(R.id.wind_value);

        return view;
    }

    public void getCityAndCountryCode(String cityName, String countryCode){
        this.cityName = cityName;
        this.countryCode = countryCode;
        Log.d("flow", "This city and country code: " + this.cityName + "," + this.countryCode);
        setUpRetrofit();
    }

    public void setUpRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient();

        httpClient.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        WeatherApi apiWeather = retrofit.create(WeatherApi.class);

        Call<WeatherData> api  = apiWeather.getCurrentCityWeather(this.cityName, this.countryCode);

                api.enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Response<WeatherData> response, Retrofit retrofit) {
                        kelvin = response.body().getMain().getTemp();
                        Log.d("kelvin", "Tempurate in kelvin is: " + kelvin);
                        kelvinToCelsius = kelvin - 273.15;
                        celsiusToFahrenheit = (int) (kelvinToCelsius * 1.8) + 32;
                        tempTextView.setText(Integer.toString(celsiusToFahrenheit) + "\u2109");
                        conditionTextView.setText(response.body().getWeather().get(0).getMain());
                        humidityTextValue.setText(Integer.toString(response.body().getMain().getHumidity()));
                        //windTextValue.setText(response.body().);

                        switch (response.body().getWeather().get(0).getIcon()) {
                            case "01d":
                                weatherImage.setImageResource(R.drawable.clearsky_day_icon);
                                break;
                            case "02d":
                                weatherImage.setImageResource(R.drawable.fewclouds_day_icon);
                                break;
                            case "03d":
                                weatherImage.setImageResource(R.drawable.scatteredclouds_day_icon);
                                break;
                            case "04d":
                                weatherImage.setImageResource(R.drawable.brokenclouds_day_icon);
                                break;
                            case "09d":
                                weatherImage.setImageResource(R.drawable.rainshower_day_icon);
                                break;
                            case "10d":
                                weatherImage.setImageResource(R.drawable.rain_day_icon);
                                break;
                            case "11d":
                                weatherImage.setImageResource(R.drawable.thunderstorm_day_icon);
                                break;
                            case "13d":
                                weatherImage.setImageResource(R.drawable.snow_day_icon);
                                break;
                            case "50d":
                                weatherImage.setImageResource(R.drawable.mist_day_icon);
                                break;
                            case "01n":
                                weatherImage.setImageResource(R.drawable.clearsky_night_icon);
                                break;
                            case "02n":
                                weatherImage.setImageResource(R.drawable.fewclouds_night_icon);
                                break;
                            case "03n":
                                weatherImage.setImageResource(R.drawable.scatteredclouds_night_icon);
                                break;
                            case "04n":
                                weatherImage.setImageResource(R.drawable.brokenclouds_night_icon);
                                break;
                            case "09n":
                                weatherImage.setImageResource(R.drawable.rainshower_night_icon);
                                break;
                            case "10n":
                                weatherImage.setImageResource(R.drawable.rain_night_icon);
                                break;
                            case "11n":
                                weatherImage.setImageResource(R.drawable.thunderstorm_night_icon);
                                break;
                            case "13n":
                                weatherImage.setImageResource(R.drawable.snow_night_icon);
                                break;
                            case "50n":
                                weatherImage.setImageResource(R.drawable.mist_night_icon);
                                break;
                        }
                    }

                    @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
