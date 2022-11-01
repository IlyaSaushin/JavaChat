package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("email") public String email;
    @SerializedName("username") public String username;
    @SerializedName("bio") public String bio;
    @SerializedName("pic") public String pic;

    public UserInfo(String email, String username, String bio, String pic) {
        this.email = email;
        this.username = username;
        this.bio = bio;
        this.pic = pic;
    }
}
