package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class RoomResponseDto {

    @SerializedName("id") String id;
    @SerializedName("name") String name;
    @SerializedName("author") String author;
    @SerializedName("private") String isPrivate;

    public RoomResponseDto(String id, String name, String author, String isPrivate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isPrivate = isPrivate;
    }
}