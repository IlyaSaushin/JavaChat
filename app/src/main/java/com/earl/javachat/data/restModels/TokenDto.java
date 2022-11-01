package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class TokenDto {

    @SerializedName("token") String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
