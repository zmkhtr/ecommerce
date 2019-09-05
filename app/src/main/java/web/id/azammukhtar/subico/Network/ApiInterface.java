package web.id.azammukhtar.subico.Network;


import io.reactivex.Flowable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import web.id.azammukhtar.subico.Model.Cart.Cart;
import web.id.azammukhtar.subico.Model.Cart.Cartproduct;
import web.id.azammukhtar.subico.Model.Favorite.Favorite;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Model.ProductDetail.ProductDetail;
import web.id.azammukhtar.subico.Model.Profile.Profile;
import web.id.azammukhtar.subico.Model.UserLogin.User;

public interface ApiInterface {

    @POST("product")
    Flowable<Response<Product>> getProducts(@Header("Authorization") String token, @Query("page") int page);

    @FormUrlEncoded
    @POST("product")
    Call<Product> searchFilterProducts(@Header("Authorization") String token,
                                                     @Query("page") int page,
                                                     @Field("keyword") String keyword,
                                                     @Field("subcategory") String subcategory,
                                                     @Field("sort") String sort);

//    @FormUrlEncoded
//    @POST("product")
//    Flowable<Response<Product>> filterPriceProducts(@Header("Authorization") String token,
//                                                    @Query("page") int page,
//                                                    @Field("sort") String sort);

//    @GET("whistlist")
//    Flowable<Response<Favorite>> getFavorite(@Header("Authorization") String token, @Query("page") int page);


    @GET("whistlist")
    Call<Favorite> getFavorite(@Header("Authorization") String token, @Query("page") int page);


//    @GET("whistlist")
//    Call<Favorite> getFavorite(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("whistlist")
    Call<Favorite> addFavorite(@Header("Authorization") String token,
                               @Field("product_id") int productId,
                               @Field("qty") int quantity);

    @DELETE("whistlist/{id}")
    Single<Favorite> deleteFavorite(@Header("Authorization") String token, @Path("id") int id);

    @GET("cart")
    Call<Cartproduct> getCart(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("cart")
    Call<Cartproduct> addCart(@Header("Authorization") String token,
                       @Field("product_id") int productId,
                       @Field("qty") int quantity,
                       @Field("size") String size,
                       @Field("color") String color);

    @DELETE("cart/{id}")
    Call<Cartproduct> deleteCart(@Header("Authorization") String token, @Path("id") String id);

    @GET("product/{id}")
    Single<ProductDetail> getProductsDetail(@Header("Authorization") String token, @Path("id") int id);

    @FormUrlEncoded
    @POST("login")
    Single<User> doLogin(@Field("username") String username,
                         @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Single<User> doRegister(@Field("name") String name,
                            @Field("username") String username,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("c_password") String RePassword);


    @GET("profile")
    Single<Profile> getProfileDetail(@Header("Authorization") String token);

}
