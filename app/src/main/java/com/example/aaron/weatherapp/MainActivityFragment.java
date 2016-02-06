package com.example.aaron.weatherapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText cityEditText, countryEditText;
    OnCurrentWeather mCurrentWeather;
    private Button setButton;
    private String cityName, countryCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        cityEditText = (EditText) view.findViewById(R.id.city);
        //countryEditText = (EditText) view.findViewById(R.id.country);

        setButton = (Button) view.findViewById(R.id.set_button);
        setButton.setOnClickListener(onButtonClicked);

        return view;
    }

    public interface OnCurrentWeather {
        void currentWeather(String cityName);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCurrentWeather = (OnCurrentWeather) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement OnCurrentWeather");
        }
    }

    View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            cityName = cityEditText.getText().toString();
            //countryCode = countryEditText.getText().toString();

            cityEditText.getText().clear();
            //countryEditText.getText().clear();

            mCurrentWeather.currentWeather(cityName);
        }
    };
}
