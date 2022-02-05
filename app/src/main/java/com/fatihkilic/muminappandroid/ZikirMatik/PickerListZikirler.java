package com.fatihkilic.muminappandroid.ZikirMatik;

import com.fatihkilic.muminappandroid.User.PickerListCountry;

import java.util.ArrayList;

public class PickerListZikirler {

    private static ArrayList<PickerListZikirler> pickerListZikirlerArrayList;


    private int id;
    private String zikirler;

    public PickerListZikirler(int id, String zikirler) {
        this.id = id;
        this.zikirler = zikirler;
    }


    public static void initCountryList () {

        pickerListZikirlerArrayList = new ArrayList();

        PickerListZikirler z1 = new PickerListZikirler(0,"Subhanallah");
        pickerListZikirlerArrayList.add(z1);

        PickerListZikirler z2 = new PickerListZikirler(1,"İstiğfar");
        pickerListZikirlerArrayList.add(z2);

        PickerListZikirler z3 = new PickerListZikirler(2,"Salatı Tefriciye");
        pickerListZikirlerArrayList.add(z3);

        PickerListZikirler z4 = new PickerListZikirler(3,"Salavatı Şerif");
        pickerListZikirlerArrayList.add(z4);

        PickerListZikirler z5 = new PickerListZikirler(4,"Hasbunallah");
        pickerListZikirlerArrayList.add(z5);

        PickerListZikirler z6 = new PickerListZikirler(5,"Kelime-i Tevhid");
        pickerListZikirlerArrayList.add(z6);

        PickerListZikirler z7 = new PickerListZikirler(6,"Ben Ekleyeceğim");
        pickerListZikirlerArrayList.add(z7);

    }


    public static ArrayList<PickerListZikirler> getPickerListZikirlerArrayList() {
        return pickerListZikirlerArrayList;
    }


    public static String[] zikirNames() {

        String[] zikirNames = new String[pickerListZikirlerArrayList.size()];
        for(int i = 0; i < pickerListZikirlerArrayList.size(); i++) {

            zikirNames[i] = pickerListZikirlerArrayList.get(i).zikirler;

        }

        return zikirNames;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZikirler() {
        return zikirler;
    }

    public void setZikirler(String zikirler) {
        this.zikirler = zikirler;
    }
}
