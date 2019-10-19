
package com.aflowz.ecommerce.Network.ResponseModel.ResponseCart;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CartDetailData extends RealmObject {

    @SerializedName("cart_id")
    @Expose
    @PrimaryKey
    private String cartId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("images")
    @Expose
    private String images;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!CartDetailData.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        final CartDetailData other = (CartDetailData) obj;
        if (this.cartId != other.cartId) {
            return false;
        }

        if (this.userId != other.userId) {
            return false;
        }

        if (this.productId != other.productId) {
            return false;
        }

        if (this.name != other.name) {
            return false;
        }


        if (this.price != other.price) {
            return false;
        }


        if (this.color != other.color) {
            return false;
        }

        if (this.size != other.size) {
            return false;
        }

        if (this.qty != other.qty) {
            return false;
        }

        if (this.weight != other.weight) {
            return false;
        }

        if (this.images != other.images) {
            return false;
        }

        return true;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
