package com.earl.javachat.ui.chat.rooms.newMessage;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;

interface ChooseContactContract {

    interface Presenter extends BasePresenter {
        void fetchContacts(String token, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
