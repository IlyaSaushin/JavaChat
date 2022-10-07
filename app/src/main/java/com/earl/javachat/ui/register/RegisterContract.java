package com.earl.javachat.ui.register;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.models.CurrentUser;

import java.util.HashMap;

public interface RegisterContract {

    interface Presenter extends BasePresenter {
        void register(CurrentUser.BaseCurrentUser currentUser, OperationResultListener callback);
        void saveAccountDetails(HashMap<String, Object> userDetails, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
