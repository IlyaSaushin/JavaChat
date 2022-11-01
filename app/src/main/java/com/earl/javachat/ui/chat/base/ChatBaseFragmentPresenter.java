package com.earl.javachat.ui.chat.base;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;

import javax.inject.Inject;

public class ChatBaseFragmentPresenter implements ChatBaseFragmentContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    public ChatBaseFragmentPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchUserInfo(String token, OperationResultListener callback) {
        repository.fetchUserInfo(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
