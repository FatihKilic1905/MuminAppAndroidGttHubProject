package com.fatihkilic.muminappandroid.DayInfo;

public class mealOfDayModel {

    int No;
    public String eatMaterials;
    public String eatName;
    public String eatPerson;
    public String eatRecipe;
    public String eatTime;
    public String eatUrl;
    public String eatImage;

    public mealOfDayModel(int no, String eatMaterials, String eatName, String eatPerson, String eatRecipe, String eatTime, String eatUrl, String eatImage) {
        No = no;
        this.eatMaterials = eatMaterials;
        this.eatName = eatName;
        this.eatPerson = eatPerson;
        this.eatRecipe = eatRecipe;
        this.eatTime = eatTime;
        this.eatUrl = eatUrl;
        this.eatImage = eatImage;
    }
}
