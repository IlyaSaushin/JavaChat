package com.earl.javachat.data.models;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public class LoginDto {

    @SerializedName("input") String input;
    @SerializedName("password") String password;

    public LoginDto(String input, String password) {
        this.input = input;
        this.password = password;
    }
}