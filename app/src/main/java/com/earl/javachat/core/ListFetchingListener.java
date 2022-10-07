package com.earl.javachat.core;

import com.earl.javachat.data.models.CurrentUser;

import java.util.List;

public interface ListFetchingListener {
    void success(List<CurrentUser.UserDetails> users);
    void fail(Exception exception);
}
