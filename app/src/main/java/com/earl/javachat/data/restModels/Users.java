package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("email") String id;
    @SerializedName("username") String username;
    @SerializedName("password") String password;

    public Users(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}