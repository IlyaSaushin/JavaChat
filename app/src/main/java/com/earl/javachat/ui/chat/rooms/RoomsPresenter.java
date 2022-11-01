package com.earl.javachat.ui.chat.rooms;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;

import javax.inject.Inject;

public class RoomsPresenter implements RoomsContract.Presenter {

    @Inject
    Repository.BaseRepository baseRepository;

    public RoomsPresenter(Repository.BaseRepository repository) {
        this.baseRepository = repository;
    }

    @Override
    public void fetchRoomsForUser(String token, OperationResultListener callback) {
        baseRepository.fetchRoomsForUser(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
