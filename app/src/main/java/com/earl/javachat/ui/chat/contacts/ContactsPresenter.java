package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;
import com.earl.javachat.data.restModels.RemoveUserFromContactsDto;

import javax.inject.Inject;

public class ContactsPresenter implements ContactsContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    @Override
    public void removeUserFromContacts(RemoveUserFromContactsDto removeUserFromContactsDto) {
        repository.removeUserFromContacts(removeUserFromContactsDto);
    }

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
