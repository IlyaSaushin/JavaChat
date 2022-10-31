package com.earl.javachat.ui.chat.messages;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.UsersListFetchingResultListener;

public interface ChatContract  {

    interface Presenter extends BasePresenter {
//        void signOut();
//        void fetchUsersList(UsersListFetchingResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
