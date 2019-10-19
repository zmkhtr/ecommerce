package com.aflowz.ecommerce.UI.CartActivity;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.UI.Account.AccountContract;

public class CartPresenter implements CartContract.Presenter, CartContract.GetCartRepository.OnFinishedListener {

    private CartContract.View view;
    private CartContract.GetCartRepository cartRepository;

    CartPresenter(CartContract.View view, CartContract.GetCartRepository cartRepository) {
        this.view = view;
        this.cartRepository = cartRepository;
    }


    @Override
    public void onSuccess(String message, CartDetailData cartDetailData) {
        if (view != null) {
            view.hideLoading();
            view.openNewActivity();
            view.showSuccessMessage(message, cartDetailData);
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
    public void removeCart(String id, CartDetailData cartDetailData) {
        if (view != null){
            cartRepository.proceedRemove(this, id, cartDetailData);
        }
    }
}
