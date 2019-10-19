package com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CourierResponse extends RealmObject {

    @SerializedName("key_kurir")
    @Expose
    @PrimaryKey
    private String keyKurir;
    @SerializedName("nama_kurir")
    @Expose
    private String namaKurir;

    public String getKeyKurir() {
        return keyKurir;
    }

    public void setKeyKurir(String keyKurir) {
        this.keyKurir = keyKurir;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

}