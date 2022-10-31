package com.earl.javachat.data;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.models.LoginDto;
import com.earl.javachat.data.models.RegisterDto;
import com.earl.javachat.data.retrofit.Service;

import javax.inject.Inject;

public interface Repository {

    String logIn(LoginDto user, OperationResultListener callback);

    String register(RegisterDto registerDto, OperationResultListener callback);

    class BaseRepository implements Repository {

        @Inject
        Service service;

        @Override
        public String logIn(LoginDto user, OperationResultListener callback) {
            String token = null;
            try {
                token = service.login(user).toString();
                callback.success();
            } catch (Exception e) {
                callback.fail(e);
            }
            return token;
        }


        @Override
        public String register(RegisterDto registerDto, OperationResultListener callback) {
            return null;
        }
    }
}