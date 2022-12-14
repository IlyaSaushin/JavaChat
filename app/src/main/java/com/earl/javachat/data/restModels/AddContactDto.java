package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class AddContactDto {

    @SerializedName("userUsername") public String userUsername;
    @SerializedName("contactUsername") public String contactUsername;

    public AddContactDto(String userUsername, String contactUsername) {
        this.userUsername = userUsername;
        this.contactUsername = contactUsername;
    }
}
