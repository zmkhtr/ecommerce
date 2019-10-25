package com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocalCheckoutResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("address")
    @Expose
    private Address address;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
