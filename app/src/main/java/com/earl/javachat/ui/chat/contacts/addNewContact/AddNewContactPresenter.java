package com.earl.javachat.ui.chat.contacts.addNewContact;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.data.Repository;
import com.earl.javachat.data.restModels.AddContactDto;

import javax.inject.Inject;

public class AddNewContactPresenter  implements AddNewContactContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    @Override
    public void addUserToContacts(AddContactDto addContactDto) {
//        repository.addUserToContacts(userId, callback);
    }

    public AddNewContactPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchUsersList(OperationResultListener callback) {
        repository.fetchAllUsers(callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
