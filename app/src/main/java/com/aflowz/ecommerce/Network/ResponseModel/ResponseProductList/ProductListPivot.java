
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class ProductListPivot extends RealmObject implements Parcelable {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productId);
        dest.writeString(this.categoryId);
    }

    public ProductListPivot() {
    }

    protected ProductListPivot(Parcel in) {
        this.productId = in.readString();
        this.categoryId = in.readString();
    }

    public static final Creator<ProductListPivot> CREATOR = new Creator<ProductListPivot>() {
        @Override
        public ProductListPivot createFromParcel(Parcel source) {
            return new ProductListPivot(source);
        }

        @Override
        public ProductListPivot[] newArray(int size) {
            return new ProductListPivot[size];
        }
    };
}
