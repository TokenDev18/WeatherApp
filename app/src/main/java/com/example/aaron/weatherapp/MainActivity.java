package com.example.aaron.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;


public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnCurrentWeather{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void currentWeather(String cityName, String countryCode) {
        Log.d("flow", "The city and countryCode: " + cityName + countryCode);
        CurrentWeatherFragment newFragment = (CurrentWeatherFragment)getFragmentManager().findFragmentById(R.id.current_layout);
        newFragment.getCityAndCountryCode(cityName, countryCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}


