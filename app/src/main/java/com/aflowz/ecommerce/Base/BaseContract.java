package com.aflowz.ecommerce.Base;

public interface BaseContract {
    interface BaseView<T> {
        void showLoading();
        void hideLoading();
    }
    interface BasePresenter {
    }
}
