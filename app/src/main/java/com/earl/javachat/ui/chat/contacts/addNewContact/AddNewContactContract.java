package com.earl.javachat.ui.chat.contacts.addNewContact;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.core.UsersListFetchingResultListener;

public interface AddNewContactContract {

    interface Presenter extends BasePresenter {
        void fetchUsersList(UsersListFetchingResultListener callback);
        void addUserToContacts(String userId, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
