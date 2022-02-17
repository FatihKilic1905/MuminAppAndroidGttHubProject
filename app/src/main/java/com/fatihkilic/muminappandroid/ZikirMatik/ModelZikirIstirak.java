package com.fatihkilic.muminappandroid.ZikirMatik;

public class ModelZikirIstirak {

    public String nickNameIstirak;
    public String zikirNameIstirak;
    public String zikirCountIstirak;
    public String endDateIstirak;
    public String emailIstirak;
    public String documentIdIstirak;
    public Integer zikirCompleteCount;
    public Integer zikirMyCompleteCount;
    public Integer zikirMyCount;

    public ModelZikirIstirak(String nickNameIstirak, String zikirNameIstirak, String zikirCountIstirak, String endDateIstirak, String emailIstirak, String documentIdIstirak, Integer zikirCompleteCount, Integer zikirMyCompleteCount, Integer zikirMyCount) {
        this.nickNameIstirak = nickNameIstirak;
        this.zikirNameIstirak = zikirNameIstirak;
        this.zikirCountIstirak = zikirCountIstirak;
        this.endDateIstirak = endDateIstirak;
        this.emailIstirak = emailIstirak;
        this.documentIdIstirak = documentIdIstirak;
        this.zikirCompleteCount = zikirCompleteCount;
        this.zikirMyCompleteCount = zikirMyCompleteCount;
        this.zikirMyCount = zikirMyCount;
    }
}
