
package web.id.azammukhtar.subico.Model.ProductDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product {

    @SerializedName("id")
    @Expose
    @PrimaryKey
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
    private RealmList<String> color = null;
    @SerializedName("size")
    @Expose
    private RealmList<String> size = null;
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
    private RealmList<String> images = null;
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
    private RealmList<SubCategory> subCategory = null;
    @SerializedName("city")
    @Expose
    private City city;

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

    public RealmList<String> getColor() {
        return color;
    }

    public void setColor(RealmList<String> color) {
        this.color = color;
    }

    public RealmList<String> getSize() {
        return size;
    }

    public void setSize(RealmList<String> size) {
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

    public RealmList<String> getImages() {
        return images;
    }

    public void setImages(RealmList<String> images) {
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

    public RealmList<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(RealmList<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
