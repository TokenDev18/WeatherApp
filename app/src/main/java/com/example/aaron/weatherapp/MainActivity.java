package com.example.aaron.weatherapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements MainActivityFragment.OnCurrentWeather{

    Button weatherButton;
    private String cityName = "";
    //private String countryCode = "";
    CurrentWeatherFragment currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        weatherButton = (Button) findViewById(R.id.button);
        weatherButton.setOnClickListener(onWeatherButtonClicked);
    }

    View.OnClickListener onWeatherButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(cityName.equals("")){
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(R.string.cityNcoutry_notSet);
                builder.setTitle("City and Country Not Set");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            } else {
                currentFrag = new CurrentWeatherFragment();
                currentFrag.getCityAndCountryCode(cityName);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.parent, currentFrag);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
    };

    @Override
    public void currentWeather(String cityName) {
        Log.d("flow", "The city and countryCode: " + cityName);
        this.cityName = cityName;
        //this.countryCode = countryCode;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}


