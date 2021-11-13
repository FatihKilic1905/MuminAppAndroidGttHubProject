package com.fatihkilic.muminappandroid;

import java.util.ArrayList;

public class EzanSoundList {

    private static ArrayList<EzanSoundList> EzanSoundListArray;

    private int id;
    private String soundName;

    public EzanSoundList(int id, String soundName) {
        this.id = id;
        this.soundName = soundName;
    }

    public static void initEzanSound() {

        EzanSoundList ezan1 = new EzanSoundList(0, "Kuş Sesi");
        EzanSoundListArray.add(ezan1);


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoundName() {
        return soundName;
    }

    public void setSoundName(String soundName) {
        this.soundName = soundName;
    }
}
