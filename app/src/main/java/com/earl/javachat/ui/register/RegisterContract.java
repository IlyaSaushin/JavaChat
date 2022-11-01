package com.earl.javachat.ui.register;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;
import com.earl.javachat.data.restModels.RegisterDto;

public interface RegisterContract {

    interface Presenter extends BasePresenter {
        void register(RegisterDto currentUser, OperationResultListener callback);
//        void saveAccountDetails(HashMap<String, Object> userDetails, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
