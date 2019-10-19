package com.aflowz.ecommerce.Network.ResponseModel.ResponseLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("user")
    @Expose
    private LoginTokenData loginTokenData;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LoginTokenData getLoginTokenData() {
        return loginTokenData;
    }

    public void setLoginTokenData(LoginTokenData loginTokenData) {
        this.loginTokenData = loginTokenData;
    }
}