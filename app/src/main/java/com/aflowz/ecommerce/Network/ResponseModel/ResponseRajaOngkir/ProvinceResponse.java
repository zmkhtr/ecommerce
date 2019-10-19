package com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class ProvinceResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("province")
    @Expose
    private List<ProvinceDetailResponse> province = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProvinceDetailResponse> getProvince() {
        return province;
    }

    public void setProvince(List<ProvinceDetailResponse> province) {
        this.province = province;
    }


}
