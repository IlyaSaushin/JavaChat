package com.earl.javachat.data;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.data.restModels.RegisterDto;
import com.earl.javachat.data.restModels.TokenDto;
import com.earl.javachat.data.restModels.UserInfo;
import com.earl.javachat.data.retrofit.Service;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Repository {

    void logIn(LoginDto user, OperationResultListener callback);

    void register(RegisterDto registerDto, OperationResultListener callback);

    void fetchUserInfo(String token, OperationResultListener callback);

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
                        callback.success(response.body().token);
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
                        callback.success(response.body().token);
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
    }
}
