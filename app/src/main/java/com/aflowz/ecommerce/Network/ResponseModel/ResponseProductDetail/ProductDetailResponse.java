
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("product")
    @Expose
    private ProductDetailData productDetailData;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ProductDetailData getProductDetailData() {
        return productDetailData;
    }

    public void setProductDetailData(ProductDetailData productDetailData) {
        this.productDetailData = productDetailData;
    }

}
