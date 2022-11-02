package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;

public interface ContactsContract {

    interface Presenter extends BasePresenter {
        void fetchContactsList(String token, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
