package com.fatihkilic.muminappandroid.User;

public class ModelGetFriendsRequest {


    public String frienduserName;
    public String frinedsName;
    public String friendsSurName ;
    public String friendsImage;
    public String friendsEmail;


    public ModelGetFriendsRequest(String frienduserName, String frinedsName, String friendsSurName, String friendsImage, String friendsEmail) {
        this.frienduserName = frienduserName;
        this.frinedsName = frinedsName;
        this.friendsSurName = friendsSurName;
        this.friendsImage = friendsImage;
        this.friendsEmail = friendsEmail;
    }
}
