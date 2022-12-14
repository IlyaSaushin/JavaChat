package com.earl.javachat.data;

import android.util.Log;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.restModels.AddContactDto;
import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.data.restModels.RegisterDto;
import com.earl.javachat.data.restModels.RemoveUserFromContactsDto;
import com.earl.javachat.data.restModels.RoomResponseDto;
import com.earl.javachat.data.restModels.TokenDto;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.data.restModels.UserUsernameDto;
import com.earl.javachat.data.retrofit.Service;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Repository {

    void logIn(LoginDto user, OperationResultListener callback);

    void register(RegisterDto registerDto, OperationResultListener callback);

    void fetchUserInfo(String token, OperationResultListener callback);

    void fetchRoomsForUser(String token, OperationResultListener callback);

    Observable<List<UserInfo>> fetchContacts(String token, OperationResultListener callback);

    void fetchAllUsers(String userUsername, OperationResultListener callback);

    void addUserToContacts(AddContactDto addContactDto);

    void removeUserFromContacts(RemoveUserFromContactsDto removeUserFromContactsDto);

    void logOut(String token, OperationResultListener callback);

    Observable<List<UserInfo>> fetchUsers(String userUsername, OperationResultListener callback);

    class BaseRepository implements Repository {

        @Inject
        Service service;

        public BaseRepository(Service service) {
            this.service = service;
        }

        @Override
        public void logIn(LoginDto user, OperationResultListener callback) {

            Call<TokenDto> token = service.login(user);
            token.enqueue(new Callback<TokenDto>() {
                @Override
                public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        callback.success(response.body().userToken);
                    }
                }
                @Override
                public void onFailure(Call<TokenDto> call, Throwable t) {
                    t.fillInStackTrace();
                    callback.fail(new Exception(t));
                }
            });
        }


        @Override
        public void register(RegisterDto registerDto, OperationResultListener callback) {

            Call<TokenDto> tokenDtoCall = service.register(registerDto);
            tokenDtoCall.enqueue(new Callback<TokenDto>() {
                @Override
                public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        callback.success(response.body().userToken);
                    }
                }

                @Override
                public void onFailure(Call<TokenDto> call, Throwable t) {
                    t.fillInStackTrace();
                    callback.fail(new Exception(t));
                }
            });
        }

        @Override
        public void fetchUserInfo(String token, OperationResultListener callback) {
            TokenDto tokenDto = new TokenDto(token);
            Call<UserInfo> userInfoCall = service.fetchUserInfo(tokenDto);
            userInfoCall.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                    callback.success(response.body());
                }

                @Override
                public void onFailure(Call<UserInfo> call, Throwable t) {
                    callback.fail(new Exception(t));
                }
            });
        }

        @Override
        public void fetchRoomsForUser(String token, OperationResultListener callback) {
            TokenDto tokenDto = new TokenDto(token);
            Call<List<RoomResponseDto>> listCall = service.fetchRoomsForUser(tokenDto);
            listCall.enqueue(new Callback<List<RoomResponseDto>>() {
                @Override
                public void onResponse(Call<List<RoomResponseDto>> call, Response<List<RoomResponseDto>> response) {
                    callback.success(response.body());
                }

                @Override
                public void onFailure(Call<List<RoomResponseDto>> call, Throwable t) {
                    callback.fail(new Exception(t));
                }
            });
        }

        @Override
        public Observable<List<UserInfo>> fetchContacts(String token, OperationResultListener callback) {
            TokenDto tokenDto = new TokenDto(token);
            return service.fetchContacts(tokenDto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            /*Call<List<UserInfo>> listCall = service.fetchContacts(tokenDto);
            listCall.enqueue(new Callback<List<UserInfo>>() {
                @Override
                public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                    callback.success(response.body());
                }

                @Override
                public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                    callback.fail(new Exception(t));
                }
            });*/
        }

        @Override
        public void fetchAllUsers(String userUsername, OperationResultListener callback) {
            UserUsernameDto fetchAllUsers = new UserUsernameDto(userUsername);
            /*Call<List<UserInfo>> listCall = service.fetchUsersList(fetchAllUsers);
            listCall.enqueue(new Callback<List<UserInfo>>() {
                @Override
                public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                    callback.success(response.body());
                }

                @Override
                public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                    callback.fail(new Exception(t));
                }
            });*/
        }

        @Override
        public Observable<List<UserInfo>> fetchUsers(String userUsername, OperationResultListener callback) {
            return service.fetchUsersList(new UserUsernameDto(userUsername))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

        @Override
        public void addUserToContacts(AddContactDto addContactDto) {
            Call<String> addContactCall = service.addContacts(addContactDto);
            addContactCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("tag", "onResponse: " + response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

        @Override
        public void removeUserFromContacts(RemoveUserFromContactsDto removeUserFromContactsDto) {
            Call<String> removeUserFromContact = service.removeContact(removeUserFromContactsDto);
            removeUserFromContact.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d("tag", "onResponse: " + response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });
        }

        @Override
        public void logOut(String token, OperationResultListener callback) {
            // todo
        }
    }
}
