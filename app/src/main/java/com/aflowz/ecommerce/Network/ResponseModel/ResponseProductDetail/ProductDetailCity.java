
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ProductDetailCity {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("provinceId")
    @Expose
    private String provinceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("province")
    @Expose
    private ProductDetailProvince productDetailProvince;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDetailProvince getProductDetailProvince() {
        return productDetailProvince;
    }

    public void setProductDetailProvince(ProductDetailProvince productDetailProvince) {
        this.productDetailProvince = productDetailProvince;
    }

}
