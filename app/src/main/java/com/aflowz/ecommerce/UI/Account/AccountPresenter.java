package com.aflowz.ecommerce.UI.Account;

import android.accounts.Account;

import com.aflowz.ecommerce.UI.LoginRegisterActivity.LoginRegisterContract;
import com.aflowz.ecommerce.UI.LoginRegisterActivity.LoginRegisterRepository;

public class AccountPresenter implements AccountContract.Presenter, AccountContract.GetAccountRepository.OnFinishedListener {

    private AccountContract.View view;
    private AccountContract.GetAccountRepository accountRepository;

    AccountPresenter(AccountContract.View view, AccountContract.GetAccountRepository accountRepository) {
        this.view = view;
        this.accountRepository = accountRepository;
    }


    @Override
    public void onSuccess(String message) {
        if (view != null) {
            view.hideLoading();
            view.openNewActivity();
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

    @Override
    public void doEdit(String name, String phone, String address) {
        if (view != null) {
            accountRepository.proceedEdit(this, name, phone, address);
            view.showLoading();
        }
    }
}
