package com.fatihkilic.muminappandroid.User;

import java.util.ArrayList;

public class PickerListGender {

    private static ArrayList<PickerListGender> pickerListGenderArrayList;


    private int id;
    private String gender;

    public PickerListGender(int id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public static void initgenderList () {

        pickerListGenderArrayList = new ArrayList<>();

        PickerListGender kadin = new PickerListGender(0,"Kadın");
        pickerListGenderArrayList.add(kadin);

        PickerListGender erkek = new PickerListGender(1,"Erkek");
        pickerListGenderArrayList.add(erkek);

    }

    public static ArrayList<PickerListGender> getPickerListGenderArrayList() {
        return pickerListGenderArrayList;
    }

    public static String[] genderNames() {

        String[] genderNames = new String[pickerListGenderArrayList.size()];
        for(int i = 0; i < pickerListGenderArrayList.size(); i++) {

            genderNames[i] = pickerListGenderArrayList.get(i).gender;

        }

        return genderNames;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
