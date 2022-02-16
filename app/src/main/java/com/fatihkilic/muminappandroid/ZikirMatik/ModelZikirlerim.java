package com.fatihkilic.muminappandroid.ZikirMatik;

public class ModelZikirlerim {


    public String zikirName;
    public Integer zikirCompleteCount;
    public Integer zikirCount;
    public String endDateMyzikir;

    public ModelZikirlerim(String zikirName, Integer zikirCompleteCount, Integer zikirCount, String endDateMyzikir) {
        this.zikirName = zikirName;
        this.zikirCompleteCount = zikirCompleteCount;
        this.zikirCount = zikirCount;
        this.endDateMyzikir = endDateMyzikir;
    }
}
