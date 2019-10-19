
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailRentCity {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("provinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("province")
    @Expose
    private ProductDetailRentProvince productDetailRentProvince;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDetailRentProvince getProductDetailRentProvince() {
        return productDetailRentProvince;
    }

    public void setProductDetailRentProvince(ProductDetailRentProvince productDetailRentProvince) {
        this.productDetailRentProvince = productDetailRentProvince;
    }

}
