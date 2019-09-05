
package web.id.azammukhtar.subico.Model.Favorite;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Favorite {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("wishlists")
    @Expose
    private Wishlists wishlists;

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

    public Wishlists getWishlists() {
        return wishlists;
    }

    public void setWishlists(Wishlists wishlists) {
        this.wishlists = wishlists;
    }

}
