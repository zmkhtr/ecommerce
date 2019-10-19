package com.aflowz.ecommerce.UI.ProductActivity;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ProductDataSource extends PageKeyedDataSource<Integer, ProductListDetailData> {

    private static final int FIRST_PAGE = 1;
    static String KEYWORD;
    static String SUB_CATEGORY;
    static String CATEGORY;
    static String SORT;
    static boolean RENT;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ProductListDetailData> callback) {

        Timber.d("Filter LoadInitial : " + KEYWORD + " " + SUB_CATEGORY + " " + CATEGORY + " " + SORT);
        if (RENT){
            ApiNetwork.getApiInterface().searchFilterProductsRent(FIRST_PAGE, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            if (response.body() != null) {
                                callback.onResult(response.body().getProduct().getData(), null, FIRST_PAGE + 1);
                            }
                            Timber.d("success get paging product ");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product ");
                        }
                    });
        } else {
            ApiNetwork.getApiInterface().searchFilterProducts(FIRST_PAGE, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            if (response.body() != null) {
                                callback.onResult(response.body().getProduct().getData(), null, FIRST_PAGE + 1);
                            }
                            Timber.d("success get paging product ");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product ");
                        }
                    });
        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ProductListDetailData> callback) {

        Timber.d("Filter LoadBefore : " + KEYWORD + " " + SUB_CATEGORY + " " + CATEGORY + " " + SORT);

        if (RENT){
            ApiNetwork.getApiInterface().searchFilterProductsRent(params.key, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                            if (response.body() != null) {

                                //passing the loaded data
                                //and the previous page key
                                callback.onResult(response.body().getProduct().getData(), adjacentKey);
                            }
                            Timber.d("success get paging product load before");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product load before");
                        }
                    });
        } else {
            ApiNetwork.getApiInterface().searchFilterProducts(params.key, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                            if (response.body() != null) {
                                //passing the loaded data
                                //and the previous page key
                                callback.onResult(response.body().getProduct().getData(), adjacentKey);
                            }
                            Timber.d("success get paging product load before");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product load before");
                        }
                    });
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ProductListDetailData> callback) {

        Timber.d("loadAfter : " + KEYWORD + " " + SUB_CATEGORY + " " + CATEGORY + " " + SORT);

        if (RENT){
            ApiNetwork.getApiInterface().searchFilterProductsRent(params.key, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            if (response.body() != null) {
                                //if the response has next page
                                //incrementing the next page number
                                Integer key;
                                boolean hasMore;
                                if (response.body().getProduct().getNextPageUrl() == null){
                                    hasMore = false;
                                    key = hasMore ? params.key + 1 : null;
                                } else {
                                    hasMore = true;
                                    key = hasMore ? params.key + 1 : null;
                                }

                                //passing the loaded data and next page value
                                callback.onResult(response.body().getProduct().getData(), key);
                            }
                            Timber.d("success get paging product load after");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product load after");
                        }
                    });
        } else {
            ApiNetwork.getApiInterface().searchFilterProducts(params.key, KEYWORD, SUB_CATEGORY, CATEGORY, SORT)
                    .enqueue(new Callback<ProductListResponse>() {
                        @Override
                        public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                            if (response.body() != null) {
                                //if the response has next page
                                //incrementing the next page number
                                Integer key;
                                boolean hasMore;
                                if (response.body().getProduct().getNextPageUrl() == null){
                                    hasMore = false;
                                    key = hasMore ? params.key + 1 : null;
                                } else {
                                    hasMore = true;
                                    key = hasMore ? params.key + 1 : null;
                                }

                                //passing the loaded data and next page value
                                callback.onResult(response.body().getProduct().getData(), key);
                            }
                            Timber.d("success get paging product load after");
                        }

                        @Override
                        public void onFailure(@NotNull Call<ProductListResponse> call, @NotNull Throwable t) {
                            Timber.e(t, "Fail fetching paging product load after");
                        }
                    });
        }
    }
}
