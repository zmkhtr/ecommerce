package web.id.azammukhtar.subico.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements Parcelable {
    private String productImg;
    private String productName;
    private String productPrice;
    private String productDetail;

    public ProductModel(String productImg, String productName, String productPrice, String productDetail) {
        this.productImg = productImg;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetail = productDetail;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.productImg);
        dest.writeString(this.productName);
        dest.writeString(this.productPrice);
        dest.writeString(this.productDetail);
    }

    protected ProductModel(Parcel in) {
        this.productImg = in.readString();
        this.productName = in.readString();
        this.productPrice = in.readString();
        this.productDetail = in.readString();
    }

    public static final Parcelable.Creator<ProductModel> CREATOR = new Parcelable.Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel source) {
            return new ProductModel(source);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };
}
