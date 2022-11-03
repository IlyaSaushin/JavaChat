package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public class UserUsernameDto {

    @SerializedName("username") public String username;

    public UserUsernameDto(String username) {
        this.username = username;
    }
}
