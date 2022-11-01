package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public
class RegisterDto {

    @SerializedName("email") String email;
    @SerializedName("username") String username;
    @SerializedName("password") String password;
    @SerializedName("pic") String pic;
    @SerializedName("bio") String bio;

    public RegisterDto(String email, String username, String password, String pic, String bio) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.pic = pic;
        this.bio = bio;
    }
}
