package com.aflowz.ecommerce.LocalDatabase;

import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCategory.CategoryResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CourierResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.ProvinceResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory.SubCategoryResponse;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;



public class AppRepository {

    public void getProfile() {
        ApiNetwork.getApiInterface().getProfileDetail()
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.addProfile(response.body().getProfileUserData());
                                SessionManager.getInstance().setRole(response.body().getProfileUserData().getRole());
                                Timber.d("profile %s", response.body().getProfileUserData().getName());
                            }
                            MainUtils.logSuccessMessage("Success Get Profile");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get profile ", t);
                    }
                });
    }

    public void getCategory() {
        ApiNetwork.getApiInterface().getCategory()
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CategoryResponse> call,@NotNull Response<CategoryResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveCategoryList(response.body().getCategoryData());
                            }
                            MainUtils.logSuccessMessage("Success get ProductDetailRentCategory");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CategoryResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get ProductDetailRentCategory ", t);
                    }
                });

    }

    public void getSubCategory() {
        ApiNetwork.getApiInterface().getSubCategory(2)
                .enqueue(new Callback<SubCategoryResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<SubCategoryResponse> call,@NotNull Response<SubCategoryResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveSubCategoryList(response.body().getSubCategories());
                            }
                            MainUtils.logSuccessMessage("Success get ProductDetailRentSubCategory");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<SubCategoryResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get ProductDetailRentSubCategory ", t);

                    }
                });

    }

    public void getCart() {
        AppDatabase.deleteAllCart();
        ApiNetwork.getApiInterface().getCart()
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CartResponse> call,@NotNull Response<CartResponse> response) {
                         if (response.isSuccessful()){
                             if (response.body() != null) {
                                 AppDatabase.saveCartList(response.body().getCart());
                             }
                             MainUtils.logSuccessMessage("Success get Cart");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CartResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Cart ", t);
                    }
                });
    }

    public void updateCart() {
        ApiNetwork.getApiInterface().getCart()
                .enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CartResponse> call,@NotNull Response<CartResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveCartList(response.body().getCart());
                            }
                            MainUtils.logSuccessMessage("Success get Cart");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<CartResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Cart ", t);
                    }
                });
    }

    public void getFavorite() {
        AppDatabase.deleteAllFavorite();
        ApiNetwork.getApiInterface().getFavorite()
                .enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FavoriteResponse> call,@NotNull Response<FavoriteResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveFavoriteList(response.body().getWishlists());
                            }
                            MainUtils.logSuccessMessage("Success get Favorite");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FavoriteResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Favorite ", t);
                    }
                });
    }

    public void updateFavorite() {
        ApiNetwork.getApiInterface().getFavorite()
                .enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FavoriteResponse> call,@NotNull Response<FavoriteResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveFavoriteList(response.body().getWishlists());
                            }
                            MainUtils.logSuccessMessage("Success get Favorite");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FavoriteResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Favorite ", t);
                    }
                });
    }



    public void getHomeProduct() {
        AppDatabase.deleteAllProduct();
        ApiNetwork.getApiInterface().getProducts(1)
                .enqueue(new Callback<ProductListResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProductListResponse> call, @NotNull Response<ProductListResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveProductList(response.body().getProduct().getData());
                            }
                            MainUtils.logSuccessMessage("Success get Favorite");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ProductListResponse> call,@ NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get ProductDetailRentData ", t);
                    }
                });
    }


    public void getProvince(){
        AppDatabase.deleteAllProvince();
        ApiNetwork.getApiInterface().getProvince()
                .enqueue(new Callback<ProvinceResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProvinceResponse> call, @NotNull Response<ProvinceResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveProvinceList(response.body().getProvince());
                            }
                            MainUtils.logSuccessMessage("Success get Province");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ProvinceResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Province ", t);
                    }
                });
    }

    public void getCourier(){
        AppDatabase.deleteAllCourier();
        ApiNetwork.getApiInterface().getCourier()
                .enqueue(new Callback<List<CourierResponse>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CourierResponse>> call, @NotNull Response<List<CourierResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.saveCourierList(response.body());
                            }
                            MainUtils.logSuccessMessage("Success get Courier");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CourierResponse>> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Courier ", t);
                    }
                });
    }


}
