package com.earl.javachat.ui.chat.contacts.addNewContact;

import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.data.Repository;
import com.earl.javachat.data.restModels.AddContactDto;
import com.earl.javachat.data.restModels.UserInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class AddNewContactPresenter  implements AddNewContactContract.Presenter {

    @Inject
    Repository.BaseRepository repository;

    @Override
    public void addUserToContacts(AddContactDto addContactDto) {
        repository.addUserToContacts(addContactDto);
    }

    public AddNewContactPresenter(Repository.BaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<List<UserInfo>> fetchUsersList(String userUsername, OperationResultListener callback) {
//        repository.fetchAllUsers(userUsername, callback);
        return repository.fetchUsers(userUsername, callback);
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroy() {

    }
}
