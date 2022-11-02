package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class AddContactDto {

    @SerializedName("user_username") public String userUsername;
    @SerializedName("contact_username") public String contactUsername;

    public AddContactDto(String userUsername, String contactUsername) {
        this.userUsername = userUsername;
        this.contactUsername = contactUsername;
    }
}
