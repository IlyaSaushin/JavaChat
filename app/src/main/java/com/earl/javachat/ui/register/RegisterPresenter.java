package com.earl.javachat.ui.register;

import com.earl.javachat.data.Repository;
import com.earl.javachat.core.SuccessOperationResultListener;
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
    public void saveAccountDetails(HashMap<String, Object> userDetails, SuccessOperationResultListener callback) {
        repository.saveDetails(userDetails, callback);
    }

    @Override
    public void register(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback) {
        repository.register(user, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
