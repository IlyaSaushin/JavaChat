package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

public class RoomResponseDto {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("private") public String isPrivate;

    public RoomResponseDto(String id, String name, String isPrivate) {
        this.id = id;
        this.name = name;
        this.isPrivate = isPrivate;
    }
}