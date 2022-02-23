package com.fatihkilic.muminappandroid.ZikirMatik;

public class ModelZikirUsers {

      public String userName;
      public Integer zikirCount;
      public Integer totalCount;
      public String inviteAnsver;
      public String emailUsers;


    public ModelZikirUsers(String userName, Integer zikirCount, Integer totalCount, String inviteAnsver,String emailUsers ) {
        this.userName = userName;
        this.zikirCount = zikirCount;
        this.totalCount = totalCount;
        this.inviteAnsver = inviteAnsver;
        this.emailUsers = emailUsers;
    }
}
