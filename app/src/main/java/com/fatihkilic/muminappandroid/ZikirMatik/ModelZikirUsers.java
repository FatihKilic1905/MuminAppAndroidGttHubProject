package com.fatihkilic.muminappandroid.ZikirMatik;

public class ModelZikirUsers {

      public String userName;
      public Integer zikirCount;
      public Integer totalCount;
      public String inviteAnsver;


    public ModelZikirUsers(String userName, Integer zikirCount, Integer totalCount, String inviteAnsver) {
        this.userName = userName;
        this.zikirCount = zikirCount;
        this.totalCount = totalCount;
        this.inviteAnsver = inviteAnsver;
    }
}
