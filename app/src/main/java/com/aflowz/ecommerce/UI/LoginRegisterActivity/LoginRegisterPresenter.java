package com.aflowz.ecommerce.UI.LoginRegisterActivity;

import timber.log.Timber;

public class LoginRegisterPresenter implements LoginRegisterContract.Presenter, LoginRegisterContract.GetLoginRepository.OnFinishedListener {
    private LoginRegisterContract.View view;
    private LoginRegisterContract.GetLoginRepository loginRegisterRepository;

    LoginRegisterPresenter(LoginRegisterContract.View view, LoginRegisterRepository loginRegisterRepository) {
        this.view = view;
        this.loginRegisterRepository = loginRegisterRepository;
    }

    @Override
    public void doLogin(String username, String password) {
        if (view != null) {
            loginRegisterRepository.proceedLogin(this, username, password);
            view.showLoading();
        }
    }

    @Override
    public void doRegister(String name, String username, String email, String password, String rePassword) {
        if (view != null) {
            loginRegisterRepository.proceedRegister(this, name, username, email, password, rePassword);
            view.showLoading();
        }
    }

    @Override
    public void onSuccess(String message) {
        Timber.d("success nih ");
        if (view != null) {
            view.hideLoading();
            view.openNewActivity();
            view.showSuccessMessage(message);
        }
    }

    @Override
    public void onWrongPassOrEmail(String message) {
        if (view != null) {
            view.hideLoading();
            view.showSuccessMessage(message);
        }
    }

    @Override
    public void onError(String message, Throwable e) {
        if (view != null) {
            view.hideLoading();
            view.showErrorMessage(message, e);
        }
    }
}
