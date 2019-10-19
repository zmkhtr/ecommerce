
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class ProductListSubCategory extends RealmObject implements Parcelable {

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
    private ProductListPivot productListPivot;
    @SerializedName("category")
    @Expose
    private ProductListCategory productListCategory;

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

    public ProductListPivot getProductListPivot() {
        return productListPivot;
    }

    public void setProductListPivot(ProductListPivot productListPivot) {
        this.productListPivot = productListPivot;
    }

    public ProductListCategory getProductListCategory() {
        return productListCategory;
    }

    public void setProductListCategory(ProductListCategory productListCategory) {
        this.productListCategory = productListCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.categoryId);
        dest.writeString(this.name);
        dest.writeString(this.deleted);
        dest.writeParcelable(this.productListPivot, flags);
        dest.writeParcelable(this.productListCategory, flags);
    }

    public ProductListSubCategory() {
    }

    protected ProductListSubCategory(Parcel in) {
        this.id = in.readInt();
        this.categoryId = in.readString();
        this.name = in.readString();
        this.deleted = in.readString();
        this.productListPivot = in.readParcelable(ProductListPivot.class.getClassLoader());
        this.productListCategory = in.readParcelable(ProductListCategory.class.getClassLoader());
    }

    public static final Creator<ProductListSubCategory> CREATOR = new Creator<ProductListSubCategory>() {
        @Override
        public ProductListSubCategory createFromParcel(Parcel source) {
            return new ProductListSubCategory(source);
        }

        @Override
        public ProductListSubCategory[] newArray(int size) {
            return new ProductListSubCategory[size];
        }
    };
}
