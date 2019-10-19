
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductListResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("product")
    @Expose
    private ProductListData product;
    @SerializedName("filter")
    @Expose
    private ProductListFilter productListFilter;


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

    public ProductListData getProduct() {
        return product;
    }

    public void setProduct(ProductListData product) {
        this.product = product;
    }

    public ProductListFilter getProductListFilter() {
        return productListFilter;
    }

    public void setProductListFilter(ProductListFilter productListFilter) {
        this.productListFilter = productListFilter;
    }

}
