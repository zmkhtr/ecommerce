package com.aflowz.ecommerce.UI.CartActivity;

import com.aflowz.ecommerce.Base.BaseContract;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.UI.Account.AccountContract;

public interface CartContract {
    interface View extends BaseContract.BaseView{
        void openNewActivity();
        void showSuccessMessage(String message, CartDetailData cartDetailData);
        void showErrorMessage(String message, Throwable e);
    }

    interface GetCartRepository {
        interface OnFinishedListener{
            void onSuccess(String message, CartDetailData cartDetailData);
            void onError(String message, Throwable e);
        }
        void proceedRemove(OnFinishedListener onFinishedListener, String id, CartDetailData cartDetailData);
    }


    interface Presenter extends BaseContract.BasePresenter{
        void removeCart(String id, CartDetailData cartDetailData);
    }
}
