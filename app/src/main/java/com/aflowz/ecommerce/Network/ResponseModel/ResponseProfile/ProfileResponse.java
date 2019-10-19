
package com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("success")
    @Expose
    private ProfileUserData profileUserData;

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

    public ProfileUserData getProfileUserData() {
        return profileUserData;
    }

    public void setProfileUserData(ProfileUserData profileUserData) {
        this.profileUserData = profileUserData;
    }

}
