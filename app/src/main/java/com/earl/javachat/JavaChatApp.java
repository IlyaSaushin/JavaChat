package com.earl.javachat;

import android.app.Application;

import com.earl.javachat.di.AppComponent;
import com.earl.javachat.di.DaggerAppComponent;

public class JavaChatApp extends Application {

    public AppComponent appComponent = DaggerAppComponent.create();

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
