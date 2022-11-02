package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;

import javax.inject.Inject;

public class ContactsPresenter implements ContactsContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

     public ContactsPresenter(Repository.BaseRepository repository) {
         this.repository = repository;
     }

    @Override
    public void fetchContactsList(String token, OperationResultListener callback) {
        repository.fetchContacts(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
