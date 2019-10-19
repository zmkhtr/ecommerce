
package com.aflowz.ecommerce.Network.ResponseModel.ResponseCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("cart")
    @Expose
    private List<CartDetailData> cart = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CartDetailData> getCart() {
        return cart;
    }

    public void setCart(List<CartDetailData> cart) {
        this.cart = cart;
    }
}
