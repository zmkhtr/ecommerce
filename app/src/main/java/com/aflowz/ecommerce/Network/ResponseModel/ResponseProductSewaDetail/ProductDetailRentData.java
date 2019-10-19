
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailRentData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("provinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("price")
    @Expose
    private List<String> price = null;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("material")
    @Expose
    private String material;
    @SerializedName("color")
    @Expose
    private List<String> color = null;
    @SerializedName("size")
    @Expose
    private List<String> size = null;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("label")
    @Expose
    private Integer label;
    @SerializedName("member")
    @Expose
    private Integer member;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("jenis_order")
    @Expose
    private String jenisOrder;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sub_category")
    @Expose
    private List<ProductDetailRentSubCategory> productDetailRentSubCategory = null;
    @SerializedName("city")
    @Expose
    private ProductDetailRentCity productDetailRentCity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getJenisOrder() {
        return jenisOrder;
    }

    public void setJenisOrder(String jenisOrder) {
        this.jenisOrder = jenisOrder;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductDetailRentSubCategory> getProductDetailRentSubCategory() {
        return productDetailRentSubCategory;
    }

    public void setProductDetailRentSubCategory(List<ProductDetailRentSubCategory> productDetailRentSubCategory) {
        this.productDetailRentSubCategory = productDetailRentSubCategory;
    }

    public ProductDetailRentCity getProductDetailRentCity() {
        return productDetailRentCity;
    }

    public void setProductDetailRentCity(ProductDetailRentCity productDetailRentCity) {
        this.productDetailRentCity = productDetailRentCity;
    }

}
