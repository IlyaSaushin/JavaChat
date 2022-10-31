package com.earl.javachat.data.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kotlinx.serialization.Serializable;

class Token {

    @SerializedName("token") String token;

    public Token(String token) {
        this.token = token;
    }
}

class RoomsResponse {

    @SerializedName("id") String id;
    @SerializedName("name") String name;
    @SerializedName("author") String author;
    @SerializedName("private") String isPrivate;

    public RoomsResponse(String id, String name, String author, String isPrivate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isPrivate = isPrivate;
    }
}

class Users {

    @SerializedName("email") String id;
    @SerializedName("username") String username;
    @SerializedName("password") String password;

    public Users(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}



@Serializable
class MessageResponseDto {
    @SerializedName("message_id") String message_id;
    @SerializedName("room_id") String room_id;
    @SerializedName("author_id") String author_id;
    @SerializedName("timestamp") String timestamp;
    @SerializedName("message") String messageText;

    public MessageResponseDto(
            String message_id, String room_id, String author_id, String timestamp, String messageText
    ) {
        this.message_id = message_id;
         this.room_id = room_id;
         this.author_id = author_id;
         this.timestamp = timestamp;
         this.messageText = messageText;
    }
}

@Serializable
class MessageRequestDto {
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

@Serializable
class NewRoomRequestDto {

    @SerializedName("name") String name;
    @SerializedName("private") String isPrivate;
    @SerializedName("author") String author;
    @SerializedName("users") List<String> users;

    public NewRoomRequestDto(
            String name, String isPrivate, String author, List<String> users
    ) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.author = author;
        this.users = users;
    }
}

