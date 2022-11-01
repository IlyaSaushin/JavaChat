package com.earl.javachat.ui.chat.base;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;

public interface ChatBaseFragmentContract {

    interface Presenter extends BasePresenter {
        void fetchUserInfo(String token, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
