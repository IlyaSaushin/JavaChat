package com.earl.javachat.ui.chat.rooms;

import com.earl.javachat.data.Repository;

import javax.inject.Inject;

public class ChatPresenter implements ChatContract.Presenter {

    @Inject
    Repository.BaseRepository baseRepository;

/*   @Override
    public void fetchUsersList(UsersListFetchingResultListener callback) {
//        baseRepository.fetchUsersList(callback);
    }*/

    public ChatPresenter(Repository.BaseRepository repository) {
        this.baseRepository = repository;
    }
/*
    @Override
    public void signOut() {
        baseRepository.signOut();
    }*/

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
