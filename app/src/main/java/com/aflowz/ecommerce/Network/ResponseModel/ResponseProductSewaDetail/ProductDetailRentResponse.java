
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailRentResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("product")
    @Expose
    private ProductDetailRentData productDetailRentData;

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

    public ProductDetailRentData getProductDetailRentData() {
        return productDetailRentData;
    }

    public void setProductDetailRentData(ProductDetailRentData productDetailRentData) {
        this.productDetailRentData = productDetailRentData;
    }

}
