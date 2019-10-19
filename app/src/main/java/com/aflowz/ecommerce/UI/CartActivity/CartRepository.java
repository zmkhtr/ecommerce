package com.aflowz.ecommerce.UI.CartActivity;

import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartResponse;
import com.aflowz.ecommerce.Utils.MainUtils;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository implements CartContract.GetCartRepository{

    @Override
    public void proceedRemove(OnFinishedListener onFinishedListener, String id, CartDetailData cartDetailData) {
        ApiNetwork.getApiInterface().deleteCart(id)
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CartResponse> call, @NotNull Response<CartResponse> response) {
                        if (response.isSuccessful()){
                            onFinishedListener.onSuccess("Success remove Cart", cartDetailData);
                        }
                        MainUtils.logSuccessMessage("Success remove Cart");
                    }

                    @Override
                    public void onFailure(@NotNull Call<CartResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error remove Cart ", t);
                        onFinishedListener.onError("Error remove Cart", t);
                    }
                });
    }
}
