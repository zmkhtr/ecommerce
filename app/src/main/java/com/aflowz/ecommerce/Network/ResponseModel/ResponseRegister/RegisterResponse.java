
package com.aflowz.ecommerce.Network.ResponseModel.ResponseRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("token")
    @Expose
    private RegisterTokenData registerTokenData;
    @SerializedName("success")
    @Expose
    private RegisterUserData registerUserData;

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

    public RegisterTokenData getRegisterTokenData() {
        return registerTokenData;
    }

    public void setRegisterTokenData(RegisterTokenData registerTokenData) {
        this.registerTokenData = registerTokenData;
    }

    public RegisterUserData getRegisterUserData() {
        return registerUserData;
    }

    public void setRegisterUserData(RegisterUserData registerUserData) {
        this.registerUserData = registerUserData;
    }

}
