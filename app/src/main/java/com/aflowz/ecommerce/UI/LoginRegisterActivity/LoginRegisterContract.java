package com.aflowz.ecommerce.UI.LoginRegisterActivity;

import com.aflowz.ecommerce.Base.BaseContract;

public interface LoginRegisterContract {
    interface View extends BaseContract.BaseView{
        void openNewActivity();
        void showSuccessMessage(String message);
        void showErrorMessage(String message, Throwable e);
    }

    interface GetLoginRepository {
        interface OnFinishedListener{
            void onSuccess(String message);
            void onWrongPassOrEmail(String message);
            void onError(String message, Throwable e);
        }
        void proceedLogin(OnFinishedListener onFinishedListener, String username, String password);
        void proceedRegister(OnFinishedListener onFinishedListener, String name, String username, String email, String password, String rePassword, String phone, String socialMedia);
    }


    interface Presenter extends BaseContract.BasePresenter{
        void doLogin(String username, String password);
        void doRegister(String name, String username, String email, String password, String rePassword, String phone, String socialMedia);
    }

}
