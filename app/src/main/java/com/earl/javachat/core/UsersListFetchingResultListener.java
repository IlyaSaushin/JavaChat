package com.earl.javachat.core;

import com.earl.javachat.data.models.CurrentUser;

import java.util.List;

public interface UsersListFetchingResultListener {
    void successList(List<CurrentUser.UserDetails> users);
    void failList(Exception exception);
}
