package com.example.aaron.weatherapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText cityEditText, countryEditText;
    OnCurrentWeather mCurrentWeather;
    private Button currentButton;
    private String cityName, countryCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        cityEditText = (EditText) view.findViewById(R.id.city);
        countryEditText = (EditText) view.findViewById(R.id.country);

        currentButton = (Button) view.findViewById(R.id.current_button);
        currentButton.setOnClickListener(onCurrentButtonClicked);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCurrentWeather = (OnCurrentWeather) getActivity();
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + " must implement OnCurrentWeather");
        }
    }

    View.OnClickListener onCurrentButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            cityName = cityEditText.getText().toString();
            countryCode = countryEditText.getText().toString();

            cityEditText.getText().clear();
            countryEditText.getText().clear();

            mCurrentWeather = new OnCurrentWeather() {
                @Override
                public void currentWeather(String cityName, String countryCode) {

                }
            };

            mCurrentWeather.currentWeather(cityName, countryCode);
        }
    };

    public interface OnCurrentWeather {
        void currentWeather(String cityName, String countryCode);
    }

}
