package com.earl.javachat.ui.chat.profile;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;

import javax.inject.Inject;

public class ProfilePresenter implements ProfileContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    public ProfilePresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logOut(String token, OperationResultListener callback) {
        repository.logOut(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
