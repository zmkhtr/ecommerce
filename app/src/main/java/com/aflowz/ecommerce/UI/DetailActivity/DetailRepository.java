package com.aflowz.ecommerce.UI.DetailActivity;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartAddResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteAddResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentCategory;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentResponse;
import com.aflowz.ecommerce.Utils.MainUtils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRepository implements DetailContract.GetDetailRepository {

    private AppRepository appRepository = new AppRepository();

    @Override
    public void proceedGetDetail(OnFinishedListener onFinishedListener, int id) {
        ApiNetwork.getApiInterface().getProductsDetail(id)
                .enqueue(new Callback<ProductDetailResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProductDetailResponse> call, @NotNull Response<ProductDetailResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                onFinishedListener.onGetDetailSuccess(response.body().getProductDetailData());
                            }
                            onFinishedListener.onSuccess("Success get Detail");
                        } else {
                            onFinishedListener.onSuccess(response.code() + " : error : " + response.message());
                        }
                        MainUtils.logSuccessMessage(response.code() + " get detail status");
                    }

                    @Override
                    public void onFailure(@NotNull Call<ProductDetailResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Detail ", t);
                        onFinishedListener.onError("Error get Detail", t);
                    }
                });
    }

    @Override
    public void proceedGetDetailRent(OnFinishedListener onFinishedListener, int id) {
        ApiNetwork.getApiInterface().getProductsDetailRent(id)
                .enqueue(new Callback<ProductDetailRentResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProductDetailRentResponse> call,@NotNull Response<ProductDetailRentResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                onFinishedListener.onGetDetailRentSuccess(response.body().getProductDetailRentData());
                            }
                            onFinishedListener.onSuccess("Success get rent Detail");
                        } else {
                            onFinishedListener.onSuccess(response.code() + " : error : " + response.message());
                        }
                        MainUtils.logSuccessMessage(response.code() + "get detail rent status");
                    }


                    @Override
                    public void onFailure(@NotNull Call<ProductDetailRentResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Detail ", t);
                        onFinishedListener.onError("Error get Detail", t);
                    }
                });
    }

    @Override
    public void proceedAddToCart(OnFinishedListener onFinishedListener, int id, int quantity, String size, String color, String price) {
        ApiNetwork.getApiInterface().addCart(id,quantity,size,color, price)
                .enqueue(new Callback<CartAddResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CartAddResponse> call, @NotNull Response<CartAddResponse> response) {
                        if (response.isSuccessful()) {
                            onFinishedListener.onSuccess("Success add to cart");
                            appRepository.updateCart();
                        } else {
                            onFinishedListener.onSuccess(response.code() + " : error : " + response.message());
                        }
                        MainUtils.logSuccessMessage(response.code() + "add to cart status");
                    }

                    @Override
                    public void onFailure(@NotNull Call<CartAddResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error add to cart ", t);
                        onFinishedListener.onError("Error add to cart", t);

                    }
                });
    }


    @Override
    public void proceedAddToFavorite(OnFinishedListener onFinishedListener, int id, int quantity) {
        AppDatabase.deleteAllFavorite();
        ApiNetwork.getApiInterface().addFavorite(id)
                .enqueue(new Callback<FavoriteAddResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FavoriteAddResponse> call, @NotNull Response<FavoriteAddResponse> response) {
                        if (response.isSuccessful()) {
                            onFinishedListener.onSuccess("Success add to favorite");
                            appRepository.updateFavorite();
                        } else {
                            onFinishedListener.onSuccess(response.code() + " : error : " + response.message());
                        }
                        MainUtils.logSuccessMessage(response.code() + "add favorite status");
                    }

                    @Override
                    public void onFailure(@NotNull Call<FavoriteAddResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error add to favorite ", t);
                        onFinishedListener.onError("Error add to favorite", t);
                    }
                });
    }

    @Override
    public void proceedRemoveFavorite(OnFinishedListener onFinishedListener, int id) {
        AppDatabase.deleteAllFavorite();
        ApiNetwork.getApiInterface().deleteFavorite(id)
                .enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FavoriteResponse> call, @NotNull Response<FavoriteResponse> response) {
                        if (response.isSuccessful()) {
                            onFinishedListener.onSuccess("Success remove favorite");
                            appRepository.updateFavorite();
                        } else {
                            onFinishedListener.onSuccess(response.code() + " : error : " + response.message());
                        }
                        MainUtils.logSuccessMessage(response.code() + " remove favorite status");
                    }

                    @Override
                    public void onFailure(@NotNull Call<FavoriteResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error remove favorite ", t);
                        onFinishedListener.onError("Error remove favorite", t);

                    }
                });
    }
}
