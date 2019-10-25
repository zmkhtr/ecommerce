
package com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("orderType")
    @Expose
    private Integer orderType;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("shippingType")
    @Expose
    private String shippingType;
    @SerializedName("shippingFee")
    @Expose
    private String shippingFee;
    @SerializedName("shippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("uniquePrice")
    @Expose
    private Integer uniquePrice;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("jenis_order")
    @Expose
    private String jenisOrder;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(String shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Integer getUniquePrice() {
        return uniquePrice;
    }

    public void setUniquePrice(Integer uniquePrice) {
        this.uniquePrice = uniquePrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJenisOrder() {
        return jenisOrder;
    }

    public void setJenisOrder(String jenisOrder) {
        this.jenisOrder = jenisOrder;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeString(this.code);
        dest.writeValue(this.orderType);
        dest.writeString(this.amount);
        dest.writeString(this.shippingType);
        dest.writeString(this.shippingFee);
        dest.writeString(this.shippingMethod);
        dest.writeValue(this.uniquePrice);
        dest.writeValue(this.totalPrice);
        dest.writeString(this.status);
        dest.writeString(this.jenisOrder);
        dest.writeString(this.updatedAt);
        dest.writeString(this.createdAt);
        dest.writeValue(this.id);
    }

    public Product() {
    }

    protected Product(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.code = in.readString();
        this.orderType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.amount = in.readString();
        this.shippingType = in.readString();
        this.shippingFee = in.readString();
        this.shippingMethod = in.readString();
        this.uniquePrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.totalPrice = (Integer) in.readValue(Integer.class.getClassLoader());
        this.status = in.readString();
        this.jenisOrder = in.readString();
        this.updatedAt = in.readString();
        this.createdAt = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
