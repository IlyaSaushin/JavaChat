package com.earl.javachat.data.retrofit;

import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.data.restModels.MessageResponseDto;
import com.earl.javachat.data.restModels.NewRoomRequestDto;
import com.earl.javachat.data.restModels.RegisterDto;
import com.earl.javachat.data.restModels.RoomResponseDto;
import com.earl.javachat.data.restModels.TokenDto;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.data.restModels.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Service {

    @Headers("Content-Type: application/json")
    @POST("/register")
    Call<TokenDto> register(
            @Body RegisterDto registerDto
    );

    @Headers("Content-Type: application/json")
    @POST("/login")
    Call<TokenDto> login(
            @Body LoginDto loginDto
    );

    @Headers("Content-Type: application/json")
    @POST("/fetchRoomsForUser")
    Call<List<RoomResponseDto>> fetchRoomsForUser(
            @Body TokenDto token
    );

    @Headers("Content-Type: application/json")
    @GET("/users")
    Call<List<Users>> fetchUsersList();

    @Headers("Content-Type: application/json")
    @POST("/messages")
    Call<List<MessageResponseDto>> fetchMessagesForRoom(
            @Body TokenDto roomToken
    );

    @Headers("Content-Type: application/json")
    @POST("/addRoom")
    Call<TokenDto> addRoom(
            @Body NewRoomRequestDto room
    );

    @Headers("Content-Type: application/json")
    @POST("/fetchUserInfo")
    Call<UserInfo> fetchUserInfo(
            @Body TokenDto token
    );
}
