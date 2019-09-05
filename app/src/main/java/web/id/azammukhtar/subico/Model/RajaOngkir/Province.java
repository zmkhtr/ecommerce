
package web.id.azammukhtar.subico.Model.RajaOngkir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {

    @SerializedName("province_id")
    @Expose
    private String provinceId;
    @SerializedName("province")
    @Expose
    private String province;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
