package com.earl.javachat.core;

public interface SuccessOperationResultListener {

    void success();
    void fail(Exception exception);
}
