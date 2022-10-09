package com.earl.javachat.ui.chat.contacts;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;

public interface ContactsContract {

    interface Presenter extends BasePresenter {
        void fetchContactsList();
    }

    interface View extends BaseView<Presenter> {

    }
}
