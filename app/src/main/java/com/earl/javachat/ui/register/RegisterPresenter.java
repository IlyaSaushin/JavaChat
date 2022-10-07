package com.earl.javachat.ui.register;

import com.earl.javachat.data.Repository;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.models.CurrentUser;

import java.util.HashMap;

import javax.inject.Inject;

public class RegisterPresenter implements RegisterContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    public RegisterPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAccountDetails(HashMap<String, Object> userDetails, OperationResultListener callback) {
        repository.saveDetails(userDetails, callback);
    }

    @Override
    public void register(CurrentUser.BaseCurrentUser user, OperationResultListener callback) {
        repository.register(user, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
