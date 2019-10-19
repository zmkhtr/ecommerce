
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailRentSubCategory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("pivot")
    @Expose
    private ProductDetailRentPivot pivot;
    @SerializedName("category")
    @Expose
    private ProductDetailRentCategory productDetailRentCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public ProductDetailRentPivot getPivot() {
        return pivot;
    }

    public void setPivot(ProductDetailRentPivot pivot) {
        this.pivot = pivot;
    }

    public ProductDetailRentCategory getProductDetailRentCategory() {
        return productDetailRentCategory;
    }

    public void setProductDetailRentCategory(ProductDetailRentCategory productDetailRentCategory) {
        this.productDetailRentCategory = productDetailRentCategory;
    }

}
