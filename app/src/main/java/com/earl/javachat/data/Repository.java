package com.earl.javachat.data;

import android.util.Log;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.restModels.LoginDto;
import com.earl.javachat.data.restModels.RegisterDto;
import com.earl.javachat.data.restModels.TokenDto;
import com.earl.javachat.data.retrofit.Service;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Repository {

    String logIn(LoginDto user, OperationResultListener callback);

    String register(RegisterDto registerDto, OperationResultListener callback);

    class BaseRepository implements Repository {

        @Inject
        Service service;

        @Override
        public String logIn(LoginDto user, OperationResultListener callback) {



            Call<TokenDto> token = service.login(user);
            Log.d("tag", "logIn: service" + service);
            token.enqueue(new Callback<TokenDto>() {
                @Override
                public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                    if (response.isSuccessful()) {
                        Log.d("tag", "onResponse: -> " + response);
                        Log.d("tag", "onResponse: body  -> " + response.body());
//                        String token = response.body();
                        callback.success();
                    }
                }

                @Override
                public void onFailure(Call<TokenDto> call, Throwable t) {
                    Log.d("tag", "onFailure:  -> " + call);
                }
            });

            /*String token = null;
            try {
                token = service.login(user).toString();
                callback.success();
            } catch (Exception e) {
                callback.fail(e);
            }
            return token;*/
            return "";
        }


        @Override
        public String register(RegisterDto registerDto, OperationResultListener callback) {

            Log.d("tag", "register: service ->" + service);
            Log.d("tag", "register: dto ->" + registerDto.toString());

            Call<TokenDto> tokenDtoCall = service.register(registerDto);
            tokenDtoCall.enqueue(new Callback<TokenDto>() {
                @Override
                public void onResponse(Call<TokenDto> call, Response<TokenDto> response) {
                    if (response.isSuccessful()) {
                        Log.d("tag", "onResponse: -> " + response);
                        Log.d("tag", "onResponse: body  -> " + response.body());
//                        String token = response.body();
                        callback.success();
                    }
                }

                @Override
                public void onFailure(Call<TokenDto> call, Throwable t) {
                    Log.d("tag", "onFailure:  -> " + call);
                }
            });
            return null;
        }
    }
}

/*
NetworkService.getInstance()
        .getJSONApi()
        .getPostWithID(1)
        .enqueue(new Callback<Post>() {
@Override
public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
        Post post = response.body();

        textView.append(post.getId() + "\n");
        textView.append(post.getUserId() + "\n");
        textView.append(post.getTitle() + "\n");
        textView.append(post.getBody() + "\n");
        }

@Override
public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {

        textView.append("Error occurred while getting request!");
        t.printStackTrace();
        }
        });*/
