package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.Repository;
import com.earl.javachat.data.restModels.RemoveUserFromContactsDto;
import com.earl.javachat.data.restModels.UserInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

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
    public Observable<List<UserInfo>> fetchContactsList(String token, OperationResultListener callback) {
        return repository.fetchContacts(token, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
