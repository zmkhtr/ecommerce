
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ProductDetailSubCategory {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("pivot")
    @Expose
    private ProductDetailPivot productDetailPivot;
    @SerializedName("category")
    @Expose
    private ProductDetailCategory productDetailCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public ProductDetailPivot getProductDetailPivot() {
        return productDetailPivot;
    }

    public void setProductDetailPivot(ProductDetailPivot productDetailPivot) {
        this.productDetailPivot = productDetailPivot;
    }

    public ProductDetailCategory getProductDetailCategory() {
        return productDetailCategory;
    }

    public void setProductDetailCategory(ProductDetailCategory productDetailCategory) {
        this.productDetailCategory = productDetailCategory;
    }

}
