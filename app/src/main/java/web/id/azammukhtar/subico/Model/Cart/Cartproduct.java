
package web.id.azammukhtar.subico.Model.Cart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cartproduct {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("cart")
    @Expose
    private Cart cart;

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
