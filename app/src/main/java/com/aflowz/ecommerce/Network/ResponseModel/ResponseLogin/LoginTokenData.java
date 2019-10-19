package com.aflowz.ecommerce.Network.ResponseModel.ResponseLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginTokenData {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("token")
    @Expose
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}