package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.restModels.RemoveUserFromContactsDto;

public interface ContactsContract {

    interface Presenter extends BasePresenter {
        void fetchContactsList(String token, OperationResultListener callback);
        void removeUserFromContacts(RemoveUserFromContactsDto removeUserFromContactsDto);
    }

    interface View extends BaseView<Presenter> {

    }
}
