package com.aflowz.ecommerce.LocalDatabase;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCategory.CategoryData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CourierResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.ProvinceDetailResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.ProvinceResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory.SubCategoryData;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class AppDatabase {

    /* RAJA ONGKIR */

    public static RealmResults<ProvinceDetailResponse> getProvince() {
        return Realm.getDefaultInstance().where(ProvinceDetailResponse.class).findAll();
    }

    public static RealmResults<CourierResponse> getCourier() {
        return Realm.getDefaultInstance().where(CourierResponse.class).findAll();
    }
    static void saveProvinceList(List<ProvinceDetailResponse> provinceList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (ProvinceDetailResponse province : provinceList) {
                realm.copyToRealmOrUpdate(province);
            }
        });
    }
    public static void deleteAllProvince(){
        RealmResults<ProvinceDetailResponse> results = Realm.getDefaultInstance().where(ProvinceDetailResponse.class).findAll();
        Realm.getDefaultInstance().executeTransaction(realm -> results.deleteAllFromRealm());
    }

    static void saveCourierList(List<CourierResponse> courierResponseList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (CourierResponse courierResponse : courierResponseList) {
                realm.copyToRealmOrUpdate(courierResponse);
            }
        });
    }
    public static void deleteAllCourier(){
        RealmResults<CourierResponse> results = Realm.getDefaultInstance().where(CourierResponse.class).findAll();
        Realm.getDefaultInstance().executeTransaction(realm -> results.deleteAllFromRealm());
    }

    /* CART */
    static void saveCartList(List<CartDetailData> cartList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (CartDetailData cart : cartList) {
                realm.copyToRealmOrUpdate(cart);
            }
        });
    }

    public static void addCart(CartDetailData cart){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.insert(cart));
    }

    public static void deleteCart(CartDetailData cart){
        Realm.getDefaultInstance().executeTransaction(realm -> cart.deleteFromRealm());
    }
    public static void deleteAllCart(){
        RealmResults<CartDetailData> results = Realm.getDefaultInstance().where(CartDetailData.class).findAll();
        Realm.getDefaultInstance().executeTransaction(realm -> results.deleteAllFromRealm());
    }

    public static RealmResults<CartDetailData> getCart() {
        return Realm.getDefaultInstance().where(CartDetailData.class).findAll();
    }

    /* FAVORITE */
    static void saveFavoriteList(List<FavoriteDetailData> favoriteList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (FavoriteDetailData favorite : favoriteList) {
                realm.copyToRealmOrUpdate(favorite);
            }
        });
    }

    public static FavoriteDetailData checkFavorite(int id){
        return Realm.getDefaultInstance().where(FavoriteDetailData.class).equalTo("id", id).findFirst();
    }



    public static void addFavorite(int id){
//        Realm.getDefaultInstance().where(FavoriteDetailData.class).equalTo("id", id).getRealm();
//                .executeTransactionAsync(realm -> realm.insert(favorite));

//        Realm.getDefaultInstance().where(FavoriteDetailData.class).equalTo("id", id).getRealm()
//                .executeTransactionAsync(realm -> realm.deleteAll());
    }

    public static void deleteFavorite(int id){
        Realm.getDefaultInstance()
                .executeTransaction(realm -> {
                    FavoriteDetailData favoriteDetailData = realm.where(FavoriteDetailData.class).equalTo("id", id).findFirst();
                    favoriteDetailData.deleteFromRealm();
                });
    }

    public static void deleteAllFavorite(){
        RealmResults<FavoriteDetailData> results = Realm.getDefaultInstance().where(FavoriteDetailData.class).findAll();
        Realm.getDefaultInstance().executeTransaction(realm -> results.deleteAllFromRealm());
    }

    public static RealmResults<FavoriteDetailData> getFavorite() {
        return Realm.getDefaultInstance().where(FavoriteDetailData.class).findAllAsync();
    }

    /* PROFILE */
    public static void addProfile(ProfileUserData profile){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.insertOrUpdate(profile));
    }

    public static void deleteProfile(ProfileUserData profile){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> profile.deleteFromRealm());
//        Realm.getDefaultInstance().delete(ProfileUserData.class);
    }

    public static ProfileUserData getProfile(String username) {
        return Realm.getDefaultInstance().where(ProfileUserData.class).equalTo("username", username).findFirst();
    }

    /* CATEGORY */
    static void saveCategoryList(List<CategoryData> categoryDataList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (CategoryData categoryData : categoryDataList) {
                realm.copyToRealmOrUpdate(categoryData);
            }
        });
    }

    public static void addCategory(CategoryData categoryData){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(categoryData));
    }

    public static void deleteCategory(CategoryData categoryData){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> categoryData.deleteFromRealm());
    }

    public static RealmResults<CategoryData> getCategory() {
        return Realm.getDefaultInstance().where(CategoryData.class).findAll();
    }

    /* SUB-CATEGORY */
    static void saveSubCategoryList(List<SubCategoryData> subCategoryDataList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (SubCategoryData subCategoryData : subCategoryDataList) {
                realm.copyToRealmOrUpdate(subCategoryData);
            }
        });
    }

    public static void addSubCategory(SubCategoryData subCategoryData){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> realm.copyToRealmOrUpdate(subCategoryData));
    }

    public static void deleteSubCategory(SubCategoryData subCategoryData){
        Realm.getDefaultInstance().executeTransactionAsync(realm -> subCategoryData.deleteFromRealm());
    }

    public static RealmResults<SubCategoryData> getSubCategory() {
        return Realm.getDefaultInstance().where(SubCategoryData.class).findAll();
    }

    /* PRODUCT HOME */
    static void saveProductList(List<ProductListDetailData> productListDetailDataList) {
        Realm.getDefaultInstance().executeTransactionAsync(realm -> {
            for (ProductListDetailData product : productListDetailDataList) {
                realm.copyToRealmOrUpdate(product);
            }
        });
    }

    public static void deleteAllProduct(){
        RealmResults<ProductListDetailData> results = Realm.getDefaultInstance().where(ProductListDetailData.class).findAll();
        Realm.getDefaultInstance().executeTransaction(realm -> results.deleteAllFromRealm());
    }

    public static RealmResults<ProductListDetailData> getProduct() {
        return Realm.getDefaultInstance().where(ProductListDetailData.class).limit(10).findAll();
    }

}
