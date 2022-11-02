package com.earl.javachat.ui.chat.profile;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;

public interface ProfileContract {

    interface Presenter extends BasePresenter {
        void logOut(String token, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
