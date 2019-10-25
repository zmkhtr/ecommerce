package com.aflowz.ecommerce.UI.Admin;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.AllOrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.Datum;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListResponse;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AdminDataSource extends PageKeyedDataSource<Integer, Datum> {

    private static final int FIRST_PAGE = 1;



    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Datum> callback) {
        ApiNetwork.getApiInterface()
                .getAllOrder(FIRST_PAGE)
                .enqueue(new Callback<AllOrderResponse>() {
                    @Override
                    public void onResponse(Call<AllOrderResponse> call, Response<AllOrderResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getOrder().getData(), null, FIRST_PAGE + 1);
                            Timber.d("successfull get paging history ");
                        }
                        Timber.d("success get paging history ");
                    }

                    @Override
                    public void onFailure(Call<AllOrderResponse> call, Throwable t) {
                        Timber.e(t, "Fail fetching paging history ");

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
        ApiNetwork.getApiInterface()
                .getAllOrder(FIRST_PAGE)
                .enqueue(new Callback<AllOrderResponse>() {
                    @Override
                    public void onResponse(Call<AllOrderResponse> call, Response<AllOrderResponse> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().getOrder().getData(), adjacentKey);
                        }
                        Timber.d("success get paging history load before");
                    }

                    @Override
                    public void onFailure(Call<AllOrderResponse> call, Throwable t) {
                        Timber.e(t, "Fail fetching paging history ");
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Datum> callback) {
        ApiNetwork.getApiInterface()
                .getAllOrder(FIRST_PAGE)
                .enqueue(new Callback<AllOrderResponse>() {
                    @Override
                    public void onResponse(Call<AllOrderResponse> call, Response<AllOrderResponse> response) {
                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key;
                            boolean hasMore;
                            if (response.body().getOrder().getNextPageUrl() == null){
                                hasMore = false;
                                key = hasMore ? params.key + 1 : null;
                            } else {
                                hasMore = true;
                                key = hasMore ? params.key + 1 : null;
                            }

                            //passing the loaded data and next page value
                            callback.onResult(response.body().getOrder().getData(), key);
                        }
                        Timber.d("success get paging product load after");
                    }

                    @Override
                    public void onFailure(Call<AllOrderResponse> call, Throwable t) {
                        Timber.e(t, "Fail fetching paging history ");
                    }
                });
    }
}
