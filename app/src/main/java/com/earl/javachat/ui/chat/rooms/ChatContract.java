package com.earl.javachat.ui.chat.rooms;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;

public interface ChatContract  {

    interface Presenter extends BasePresenter {
//        void signOut();
//        void fetchUsersList(UsersListFetchingResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
