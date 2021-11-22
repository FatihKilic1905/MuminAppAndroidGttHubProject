package com.fatihkilic.muminappandroid.Ayarlar;

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

        EzanSoundListArray = new ArrayList<>();

        EzanSoundList ezan1 = new EzanSoundList(0, "Kuş Sesi");
        EzanSoundListArray.add(ezan1);

        EzanSoundList ezan2 = new EzanSoundList(1, "Kuş Sesi 2");
        EzanSoundListArray.add(ezan2);

        EzanSoundList ezan3 = new EzanSoundList(2, "Ahmad Al Nafees");
        EzanSoundListArray.add(ezan3);

        EzanSoundList ezan4 = new EzanSoundList(3, "Masjid Al Haram");
        EzanSoundListArray.add(ezan4);

        EzanSoundList ezan5 = new EzanSoundList(3, "Mishary Rashid Alafasy");
        EzanSoundListArray.add(ezan5);


    }

    public static ArrayList<EzanSoundList> getEzanSoundListArray() {
        return EzanSoundListArray;
    }

    public static  String[] ezanNames() {

        String[] names = new String[EzanSoundListArray.size()];
        for(int i = 0; i < EzanSoundListArray.size(); i++) {

            names[i] = EzanSoundListArray.get(i).soundName;

        }

        return names;
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
