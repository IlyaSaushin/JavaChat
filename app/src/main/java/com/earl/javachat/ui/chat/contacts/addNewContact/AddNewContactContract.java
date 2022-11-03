package com.earl.javachat.ui.chat.contacts.addNewContact;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.UsersListFetchingResultListener;
import com.earl.javachat.data.restModels.AddContactDto;

public interface AddNewContactContract {

    interface Presenter extends BasePresenter {
        void fetchUsersList(String userUsername, OperationResultListener callback);
        void addUserToContacts(AddContactDto addContactDto);
    }

    interface View extends BaseView<Presenter> {

    }
}
