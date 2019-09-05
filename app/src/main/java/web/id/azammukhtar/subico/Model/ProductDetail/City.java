
package web.id.azammukhtar.subico.Model.ProductDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class City {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("provinceId")
    @Expose
    private String provinceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("province")
    @Expose
    private Province province;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

}
