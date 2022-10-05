package com.earl.javachat.ui.chat;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;

public interface ChatContract  {

    interface Presenter extends BasePresenter {
        void signOut();
    }

    interface View extends BaseView<Presenter> {

    }
}
