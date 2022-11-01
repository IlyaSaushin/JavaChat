package com.earl.javachat.data.restModels;

import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.Serializable;

@Serializable
public class MessageRequestDto {
    @SerializedName("room_id") String room_id;
    @SerializedName("author_id") String author_id;
    @SerializedName("timestamp") String timestamp;
    @SerializedName("message") String messageText;

    public MessageRequestDto(
            String room_id, String author_id, String timestamp, String messageText
    ) {
        this.room_id = room_id;
        this.author_id = author_id;
        this.timestamp = timestamp;
        this.messageText = messageText;
    }
}
