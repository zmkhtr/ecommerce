package com.aflowz.ecommerce.Network;


import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.AllOrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartAddResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCategory.CategoryResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.GlobalCheckOutResponse.CheckoutGlobalResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse.LocalCheckoutResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteAddResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseLogin.LoginResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.DetailOrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.EditOrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.OrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CityResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CostResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CourierResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.DistrictResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.ProvinceResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRegister.RegisterResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory.SubCategoryResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    /* PRODUCT */
    @POST("product")
    Call<ProductListResponse> getProducts(@Query("page") int page);

    @FormUrlEncoded
    @POST("product")
    Call<ProductListResponse> searchFilterProducts(
            @Query("page") int page,
            @Field("keyword") String keyword,
            @Field("subCategory") String subcategory,
            @Field("category") String category,
            @Field("sort") String sort);

    @FormUrlEncoded
    @POST("product/sewa")
    Call<ProductListResponse> searchFilterProductsRent(
            @Query("page") int page,
            @Field("keyword") String keyword,
            @Field("subcategory") String subcategory,
            @Field("category") String category,
            @Field("sort") String sort);


    @GET("product/{id}")
    Call<ProductDetailResponse> getProductsDetail(@Path("id") int id);

    @GET("product/sewa/{id}")
    Call<ProductDetailRentResponse> getProductsDetailRent(@Path("id") int id);



    /* FAVORITE */
    @GET("whistlist")
    Call<FavoriteResponse> getFavorite();

    @FormUrlEncoded
    @POST("whistlist")
    Call<FavoriteAddResponse> addFavorite(
            @Field("product_id") int productId);

    @DELETE("whistlist/{id}")
    Call<FavoriteResponse> deleteFavorite(@Path("id") int id);


    /* CART */
    @GET("cart")
    Call<CartResponse> getCart();

    @FormUrlEncoded
    @POST("cart")
    Call<CartAddResponse> addCart(
            @Field("product_id") int productId,
            @Field("qty") int quantity,
            @Field("size") String size,
            @Field("color") String color,
            @Field("price") String price);

    @DELETE("cart/{id}")
    Call<CartResponse> deleteCart(@Path("id") String id);


    /* AUTH */
    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> doLogin(@Field("username") String username,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> doRegister(@Field("name") String name,
                                      @Field("username") String username,
                                      @Field("email") String email,
                                      @Field("password") String password,
                                      @Field("c_password") String RePassword);

    /* PROFILE */
    @GET("profile")
    Call<ProfileResponse> getProfileDetail();

    @FormUrlEncoded
    @PUT("profile/update")
    Call<ResponseBody> updateProfile(@Field("name") String name, @Field("email") String email, @Field("address") String address);

    /* CATEGORY */
    @GET("category/product")
    Call<CategoryResponse> getCategory();

    @GET("product/subcategory/{id}")
    Call<SubCategoryResponse> getSubCategory(@Path("id") int number);


    /* RAJA ONGKIR*/
    @GET("getProvince")
    Call<ProvinceResponse> getProvince();

    @GET("getKurir")
    Call<List<CourierResponse>> getCourier();

    @GET("getCity/{id}")
    Call<List<CityResponse>> getCity(@Path("id") int idProvince);


    @GET("getKecamatan/{id}")
    Call<List<DistrictResponse>> getKecamatan(@Path("id") int idCity);


    @GET("getCost/{id_city}/{key_courier}")
    Call<CostResponse> getCost(@Path("id_city") int idCity,
                               @Path("key_courier") String keyCourier);


    /* ORDER */
    @GET("allorder")
    Call<AllOrderResponse> getAllOrder(@Query("page") int page);

    @FormUrlEncoded
    @PUT("status/{id}")
    Call<EditOrderResponse> editOrder(@Path("id") int idOrder,
                                      @Field("status") String status,
                                      @Field("no_resi") String noResi);

    //    1.	UNPAID
    //    2.	PROCESS
    //    3.	CONFIRM
    //    4.	DELIVERY
    //    5.	COMPLETE
    //    6.	CANCELED


    @GET("status/{id}")
    Call<DetailOrderResponse> getDetailOrder(@Path("id") int idOrder);

    @GET("status")
    Call<OrderResponse> getOrder();


    /* CHECKOUT */
    @FormUrlEncoded
    @POST("product/checkout")
    Call<LocalCheckoutResponse> checkOutLocal(@Field("amount") String amount, //82105
                                              @Field("shippingType") String courier, // TIKI
                                              @Field("shippingFee") String shippingFee, //49861
                                              @Field("shippingMethod") String shippingMethod, //JNE City Courier Rp. 9000 ( 1-2 ) Hari
                                              @Field("jenis_order") String jenis_order, //LOCAL or GLOBAL
                                              @Field("provinceId") String provinceId, // 6
                                              @Field("cityId") String cityId, // 154
                                              @Field("address") String address); //pangeran st no 23

    @FormUrlEncoded
    @POST("product/checkout")
    Call<CheckoutGlobalResponse> checkOutGlobal(@Field("amount") String amount, //82105
                                                @Field("shippingType") String courier, // TIKI
                                                @Field("shippingFee") String shippingFee, //49861
                                                @Field("shippingMethod") String shippingMethod, //JNE City Courier Rp. 9000 ( 1-2 ) Hari
                                                @Field("jenis_order") String jenis_order, //LOCAL or GLOBAL
                                                @Field("negara") String negara, // ARAB
                                                @Field("provinsi") String provinsi, // RIYAD
                                                @Field("kota") String kota, // JEDDAH
                                                @Field("alamat") String alamat, // JL Salman Raya
                                                @Field("postal_code") String postal_code, // 19002
                                                @Field("phone") String phone); // +00129282892


}
