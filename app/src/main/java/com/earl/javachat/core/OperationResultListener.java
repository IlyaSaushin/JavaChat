package com.earl.javachat.core;

public interface OperationResultListener {

    void success();
    void fail(Exception exception);
}
