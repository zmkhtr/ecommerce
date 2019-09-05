
package web.id.azammukhtar.subico.Model.Product;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("provinceId")
    @Expose
    private String provinceId;
    @SerializedName("cityId")
    @Expose
    private String cityId;
    @SerializedName("stock")
    @Expose
    private String stock;
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
    private String label;
    @SerializedName("member")
    @Expose
    private String member;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deleted")
    @Expose
    private String deleted;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("sub_category")
    @Expose
    private List<SubCategory> subCategory = null;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
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

    public List<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeString(this.provinceId);
        dest.writeString(this.cityId);
        dest.writeString(this.stock);
        dest.writeString(this.price);
        dest.writeString(this.description);
        dest.writeString(this.weight);
        dest.writeString(this.material);
        dest.writeString(this.color);
        dest.writeString(this.size);
        dest.writeString(this.note);
        dest.writeString(this.label);
        dest.writeString(this.member);
        dest.writeString(this.images);
        dest.writeString(this.status);
        dest.writeString(this.deleted);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeList(this.subCategory);
    }

    public Datum() {
    }

    protected Datum(Parcel in) {
        this.id = in.readInt();
        this.code = in.readString();
        this.name = in.readString();
        this.provinceId = in.readString();
        this.cityId = in.readString();
        this.stock = in.readString();
        this.price = in.readString();
        this.description = in.readString();
        this.weight = in.readString();
        this.material = in.readString();
        this.color = in.readString();
        this.size = in.readString();
        this.note = in.readString();
        this.label = in.readString();
        this.member = in.readString();
        this.images = in.readString();
        this.status = in.readString();
        this.deleted = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.subCategory = new ArrayList<SubCategory>();
        in.readList(this.subCategory, SubCategory.class.getClassLoader());
    }

    public static final Parcelable.Creator<Datum> CREATOR = new Parcelable.Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel source) {
            return new Datum(source);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };
}
