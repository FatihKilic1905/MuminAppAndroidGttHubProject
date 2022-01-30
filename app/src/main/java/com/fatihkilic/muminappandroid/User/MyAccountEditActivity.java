package com.fatihkilic.muminappandroid.User;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityMyAccountEditBinding;
import com.fatihkilic.muminappandroid.databinding.ActivitySignInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.Date;

public class MyAccountEditActivity extends AppCompatActivity {

    private ActivityMyAccountEditBinding binding;

    private FirebaseAuth auth;
    private FirebaseFirestore firebaseFirestore;
    private static final String ONESIGNAL_APP_ID = "1966721c-a30c-4299-9d7a-38e084b98072";

    String userName;
    String name;
    String surName;
    String birthdayString;
    String email;
    String userDescription;
    String country;
    String province;
    String image;

    Date bithday;

    String pickerStatus;

    ArrayList<String> genderArray;
    ArrayList<String> countryArray;
    ArrayList<String> stateArray;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_edit);

        binding = ActivityMyAccountEditBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


        DatePicker bdDAteBicker = binding.birthdayDatePicker;

        bdDAteBicker.init();

        bdDAteBicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                binding.birthdayTextView.setText(dayOfMonth + monthOfYear + year);
                binding.birthdayDatePicker.setVisibility(View.INVISIBLE);



            }
        });



        binding.genderpickerList.setVisibility(View.INVISIBLE);
        binding.countrypickerList.setVisibility(View.INVISIBLE);
        binding.statepickerList.setVisibility(View.INVISIBLE);
        binding.pickerBackground.setVisibility(View.INVISIBLE);
        binding.pickerSaveButton.setVisibility(View.INVISIBLE);

        PickerListGender.initgenderList();

        binding.genderpickerList.setMaxValue(PickerListGender.getPickerListGenderArrayList().size()-1);
        binding.genderpickerList.setMinValue(0);
        binding.genderpickerList.setDisplayedValues(PickerListGender.genderNames());

        binding.genderpickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.genderTextView.setText(PickerListGender.getPickerListGenderArrayList().get(newVal).getGender());

            }
        });


        PickerListCountry.initCountryList();

        binding.countrypickerList.setMaxValue(PickerListCountry.getPickerListCountryArrayList().size()-1);
        binding.countrypickerList.setMinValue(0);
        binding.countrypickerList.setDisplayedValues(PickerListCountry.countryNames());

        binding.countrypickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.countryTextView.setText(PickerListCountry.getPickerListCountryArrayList().get(newVal).getCountry());

            }
        });



        PickerListState.initStateList();

        binding.statepickerList.setMaxValue(PickerListState.getPickerListStateArrayList().size()-1);
        binding.statepickerList.setMinValue(0);
        binding.statepickerList.setDisplayedValues(PickerListState.stateNames());



        binding.statepickerList.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                binding.provinceTextView.setText(PickerListState.getPickerListStateArrayList().get(newVal).getState());

            }
        });


        Button pickerSaveButton = binding.pickerSaveButton;
        pickerSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (pickerStatus.equals("Gender")) {

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.genderpickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);


                } else if (pickerStatus.equals("Country")){

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.countrypickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);

                    binding.countryTextView.setText("Türkiye");


                } else if (pickerStatus.equals("State")) {

                    binding.pickerBackground.setVisibility(View.INVISIBLE);
                    binding.statepickerList.setVisibility(View.INVISIBLE);
                    binding.pickerSaveButton.setVisibility(View.INVISIBLE);




                }



            }
        });



        binding.genderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.genderpickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                pickerStatus = "Gender";

            }
        });

        binding.countryTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.countrypickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                pickerStatus = "Country";

            }
        });

        binding.provinceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.statepickerList.setVisibility(View.VISIBLE);
                binding.pickerBackground.setVisibility(View.VISIBLE);
                binding.pickerSaveButton.setVisibility(View.VISIBLE);
                pickerStatus = "State";

            }
        });




    }
}