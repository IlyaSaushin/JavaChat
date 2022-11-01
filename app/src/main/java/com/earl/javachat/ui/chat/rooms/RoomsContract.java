package com.earl.javachat.ui.chat.rooms;

import com.earl.javachat.core.BasePresenter;
import com.earl.javachat.core.BaseView;
import com.earl.javachat.core.OperationResultListener;

public interface RoomsContract {

    interface Presenter extends BasePresenter {
        void fetchRoomsForUser(String token, OperationResultListener callback);
    }

    interface View extends BaseView<Presenter> {

    }
}
