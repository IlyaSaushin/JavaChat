package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class TokenDto {

    @SerializedName("userToken")
    public String userToken;

    public TokenDto(String userToken) {
        this.userToken = userToken;
    }
}
