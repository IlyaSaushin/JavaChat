package com.earl.javachat.core;

public interface OperationResultListener {

    <T> void success(T success);
    void fail(Exception exception);
}
