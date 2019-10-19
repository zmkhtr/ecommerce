
package com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoryResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("subCategories")
    @Expose
    private List<SubCategoryData> subCategories = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<SubCategoryData> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategoryData> subCategories) {
        this.subCategories = subCategories;
    }

}
