package com.earl.javachat.ui;

public interface NavigationContract {

    void login();

    void register();

    void showAddDetailsFragment(String email, String password);

    void chat();

    void showAddNewContactFragment();

    void back();

    void showProgressBar();

    void hideProgressBar();

    void closeApp();
}
