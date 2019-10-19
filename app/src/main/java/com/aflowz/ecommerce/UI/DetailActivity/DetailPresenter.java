package com.aflowz.ecommerce.UI.DetailActivity;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentData;
import com.aflowz.ecommerce.UI.Account.AccountContract;

public class DetailPresenter implements DetailContract.Presenter, DetailContract.GetDetailRepository.OnFinishedListener {


    private DetailContract.View view;
    private DetailContract.GetDetailRepository detailRepository;


    DetailPresenter(DetailContract.View view, DetailContract.GetDetailRepository detailRepository) {
        this.view = view;
        this.detailRepository = detailRepository;
    }

    @Override
    public void onSuccess(String message) {
        if (view != null) {
            view.hideLoading();
            view.showSuccessMessage(message);
        }
    }

    @Override
    public void onGetDetailSuccess(ProductDetailData productDetailData) {
        if(view != null){
            view.hideLoading();
            view.showProductDetail(productDetailData);
        }
    }

    @Override
    public void onGetDetailRentSuccess(ProductDetailRentData productDetailRentData) {
        if(view != null){
            view.hideLoading();
            view.showProductRentDetail(productDetailRentData);
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
    public void getDetail(int id) {
        if (view != null) {
            detailRepository.proceedGetDetail(this, id);
            view.showLoading();
        }
    }

    @Override
    public void getDetailRent(int id) {
        if (view != null) {
            detailRepository.proceedGetDetailRent(this, id);
            view.showLoading();
        }
    }


    @Override
    public void addToCart(int id, int quantity, String size, String color, String price) {
        if (view != null) {
            detailRepository.proceedAddToCart(this, id, quantity, size, color, price);
            view.showLoading();
        }
    }

    @Override
    public void addToFavorite(int id, int quantity) {
        if (view != null) {
            detailRepository.proceedAddToFavorite(this, id, quantity);
            view.showLoading();
        }
    }

    @Override
    public void removeFavorite(int id) {
        if (view != null) {
            detailRepository.proceedRemoveFavorite(this, id);
            view.showLoading();
        }
    }
}
