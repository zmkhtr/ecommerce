package com.aflowz.ecommerce.UI.Account;

import com.aflowz.ecommerce.Base.BaseContract;

public interface AccountContract {
    interface View extends BaseContract.BaseView{
        void openNewActivity();
        void showSuccessMessage(String message);
        void showErrorMessage(String message, Throwable e);
    }

    interface GetAccountRepository {
        interface OnFinishedListener{
            void onSuccess(String message);
            void onError(String message, Throwable e);
        }
        void proceedEdit(OnFinishedListener onFinishedListener, String name, String phone, String address);
    }


    interface Presenter extends BaseContract.BasePresenter{
        void doEdit(String name, String phone, String address);
    }

}
