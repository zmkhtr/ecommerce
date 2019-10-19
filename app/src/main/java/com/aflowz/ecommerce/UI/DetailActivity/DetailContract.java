package com.aflowz.ecommerce.UI.DetailActivity;

import com.aflowz.ecommerce.Base.BaseContract;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentData;
import com.aflowz.ecommerce.UI.Account.AccountContract;

import retrofit2.http.Field;

public interface DetailContract {
    interface View extends BaseContract.BaseView {
        void showSuccessMessage(String message);
        void showErrorMessage(String message, Throwable e);
        void showProductDetail(ProductDetailData productDetailData);
        void showProductRentDetail(ProductDetailRentData productDetailRentData);
    }

    interface GetDetailRepository {
        interface OnFinishedListener {
            void onSuccess(String message);
            void onGetDetailSuccess(ProductDetailData productDetailData);
            void onGetDetailRentSuccess(ProductDetailRentData productDetailRentData);
            void onError(String message, Throwable e);
        }

        void proceedGetDetail(OnFinishedListener onFinishedListener, int id);
        void proceedGetDetailRent(OnFinishedListener onFinishedListener, int id);
        void proceedAddToCart(OnFinishedListener onFinishedListener, int id, int quantity, String size, String color, String price);
        void proceedAddToFavorite(OnFinishedListener onFinishedListener, int id, int quantity);
        void proceedRemoveFavorite(OnFinishedListener onFinishedListener, int id);
    }

    interface Presenter extends BaseContract.BasePresenter {
        void getDetail(int id);
        void getDetailRent(int id);
        void addToCart(int id, int quantity, String size, String color, String price);
        void addToFavorite(int id, int quantity);
        void removeFavorite(int id);
    }
}
