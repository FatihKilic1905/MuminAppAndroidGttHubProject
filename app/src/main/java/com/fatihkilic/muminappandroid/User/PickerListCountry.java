package com.fatihkilic.muminappandroid.User;

import java.util.ArrayList;

public class PickerListCountry {


    private static ArrayList<PickerListCountry> pickerListCountryArrayList;


    private int id;
    private String country;

    public PickerListCountry(int id, String country) {
        this.id = id;
        this.country = country;
    }


    public static void initCountryList () {

        pickerListCountryArrayList = new ArrayList();

        PickerListCountry turkey = new PickerListCountry(0,"Türkiye");
        pickerListCountryArrayList.add(turkey);

    }


    public static ArrayList<PickerListCountry> getPickerListCountryArrayList() {
        return pickerListCountryArrayList;
    }


    public static String[] countryNames() {

        String[] countryNames = new String[pickerListCountryArrayList.size()];
        for(int i = 0; i < pickerListCountryArrayList.size(); i++) {

            countryNames[i] = pickerListCountryArrayList.get(i).country;

        }

        return countryNames;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
