
package com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavoriteDetailData extends RealmObject {


    @SerializedName("wishlist_id")
    @Expose
    @PrimaryKey
    private Integer wishlistId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
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
    private Integer stock;
    @SerializedName("price")
    @Expose
    private String price;
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
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
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
    private String images;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("jenis_order")
    @Expose
    private String jenisOrder;
    @SerializedName("deleted")
    @Expose
    private Integer deleted;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!FavoriteDetailData.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final FavoriteDetailData other = (FavoriteDetailData) obj;
        if (this.wishlistId != other.wishlistId) {
            return false;
        }

        if (this.id != other.id) {
            return false;
        }

        if (this.userId != other.userId) {
            return false;
        }
        if (this.code != other.code) {
            return false;
        }
        if (this.name != other.name) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        if (this.provinceId != other.provinceId) {
            return false;
        }

        if (this.cityId != other.cityId) {
            return false;
        }

        if (this.stock != other.stock) {
            return false;
        }

        if (this.price != other.price) {
            return false;
        }

        if (this.description != other.description) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }

        if (this.material != other.material) {
            return false;
        }

        if (this.color != other.color) {
            return false;
        }

        if (this.size != other.size) {
            return false;
        }

        if (this.note != other.note) {
            return false;
        }

        if (this.label != other.label) {
            return false;
        }
        if (this.member != other.member) {
            return false;
        }

        if (this.images != other.images) {
            return false;
        }

        if (this.status != other.status) {
            return false;
        }
        if (this.deleted != other.deleted) {
            return false;
        }

        if (this.createdAt != other.createdAt) {
            return false;
        }

        if (this.updatedAt != other.updatedAt) {
            return false;
        }

        if (this.jenisOrder != other.jenisOrder) {
            return false;
        }
        return true;
    }

    public Integer getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
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
}
