package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public class RemoveUserFromContactsDto {

    @SerializedName("userUsername") public String userUsername;
    @SerializedName("contactUsername") public String contactUsername;

    public RemoveUserFromContactsDto(String userUsername, String contactUsername) {
        this.userUsername = userUsername;
        this.contactUsername = contactUsername;
    }
}
