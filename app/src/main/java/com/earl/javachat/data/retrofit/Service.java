package com.earl.javachat.data.retrofit;

import com.earl.javachat.data.models.LoginDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {

    @Headers("Content-Type: application/json")
    @POST("/register")
    Call<Token> register(
            @Body RegisterDto registerDto
    );

    @Headers("Content-Type: application/json")
    @POST("/login")
    Call<Token> login(
            @Body LoginDto loginDto
    );

    @Headers("Content-Type: application/json")
    @POST("/fetchRoomsForUser")
    Call<List<RoomsResponse>> fetchRoomsForUser(
            @Body Token token
    );

    @Headers("Content-Type: application/json")
    @GET("/users")
    Call<List<Users>> fetchUsersList();

    @Headers("Content-Type: application/json")
    @POST("/messages")
    Call<List<MessageResponseDto>> fetchMessagesForRoom(
            @Body Token roomToken
    );

    @Headers("Content-Type: application/json")
    @POST("/addRoom")
    Call<Token> addRoom(
            @Body NewRoomRequestDto room
    );
}
