
package web.id.azammukhtar.subico.Model.Product;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pivot implements Parcelable {

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

    public Pivot() {
    }

    protected Pivot(Parcel in) {
        this.productId = in.readString();
        this.categoryId = in.readString();
    }

    public static final Parcelable.Creator<Pivot> CREATOR = new Parcelable.Creator<Pivot>() {
        @Override
        public Pivot createFromParcel(Parcel source) {
            return new Pivot(source);
        }

        @Override
        public Pivot[] newArray(int size) {
            return new Pivot[size];
        }
    };
}
