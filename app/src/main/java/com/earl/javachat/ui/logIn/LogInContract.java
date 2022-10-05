package com.earl.javachat.ui.logIn;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.SuccessOperationResultListener;
import com.earl.javachat.data.models.CurrentUser;

public interface LogInContract {

    interface Presenter extends BasePresenter {
        void logIn(CurrentUser.BaseCurrentUser user, SuccessOperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
