package com.fatihkilic.muminappandroid.ZikirMatik;

public class ZikirlerimPostModel {

    long ZikirCompleteCount;
    String nickname;
    long zikirCount;
    String zikirDescription;
    String zikirName;
    String zikirPray;
    String zikirStatus;

    public ZikirlerimPostModel(long zikirCompleteCount, String nickname, long zikirCount, String zikirDescription, String zikirName, String zikirPray, String zikirStatus) {
        this.ZikirCompleteCount = zikirCompleteCount;
        this.nickname = nickname;
        this.zikirCount = zikirCount;
        this.zikirDescription = zikirDescription;
        this.zikirName = zikirName;
        this.zikirPray = zikirPray;
        this.zikirStatus = zikirStatus;
    }
}
