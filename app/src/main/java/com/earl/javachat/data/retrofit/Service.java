package com.earl.javachat.data.retrofit;

import com.earl.javachat.data.restModels.AddContactDto;
import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.data.restModels.MessageResponseDto;
import com.earl.javachat.data.restModels.NewRoomRequestDto;
import com.earl.javachat.data.restModels.RegisterDto;
import com.earl.javachat.data.restModels.RemoveUserFromContactsDto;
import com.earl.javachat.data.restModels.RoomResponseDto;
import com.earl.javachat.data.restModels.TokenDto;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.data.restModels.UserUsernameDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
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
    @POST("/users")
    Observable<List<UserInfo>> fetchUsersList(
            @Body UserUsernameDto fetchAllUsers
    );

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

    @Headers("Content-Type: application/json")
    @POST("/contacts")
    Observable<List<UserInfo>> fetchContacts(
            @Body TokenDto token
    );

    @Headers("Content-Type: application/json")
    @POST("/addContact")
    Call<String> addContacts(
            @Body AddContactDto addContactDto
    );

    @Headers("Content-Type: application/json")
    @POST("/removeContact")
    Call<String> removeContact(
            @Body RemoveUserFromContactsDto removeUserFromContactsDto
    );
}
