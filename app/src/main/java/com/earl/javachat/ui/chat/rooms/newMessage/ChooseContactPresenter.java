package com.earl.javachat.ui.chat.rooms.newMessage;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;

public class ChooseContactPresenter implements ChooseContactContract.Presenter {

    Repository.BaseRepository repository;

    public ChooseContactPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchContacts(String token, OperationResultListener callback) {
        repository.fetchContacts(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
