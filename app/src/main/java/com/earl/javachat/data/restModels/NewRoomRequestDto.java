package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kotlinx.serialization.Serializable;

@Serializable
public class NewRoomRequestDto {

    @SerializedName("name") String name;
    @SerializedName("private") String isPrivate;
    @SerializedName("author") String author;
    @SerializedName("users")
    List<String> users;

    public NewRoomRequestDto(
            String name, String isPrivate, String author, List<String> users
    ) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.author = author;
        this.users = users;
    }
}