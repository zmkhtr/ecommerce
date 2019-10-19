
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ProductListFilter {

    @SerializedName("page")
    @Expose
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
