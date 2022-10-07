package com.earl.javachat.ui.chat.messages;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.ListFetchingListener;

public interface ChatContract  {

    interface Presenter extends BasePresenter {
        void signOut();
        void fetchUsersList(ListFetchingListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
