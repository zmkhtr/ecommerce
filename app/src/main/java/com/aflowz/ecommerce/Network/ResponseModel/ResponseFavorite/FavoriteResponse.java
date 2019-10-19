
package com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("wishlists")
    @Expose
    private List<FavoriteDetailData> wishlists = null;

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

    public List<FavoriteDetailData> getWishlists() {
        return wishlists;
    }

    public void setWishlists(List<FavoriteDetailData> wishlists) {
        this.wishlists = wishlists;
    }
}
