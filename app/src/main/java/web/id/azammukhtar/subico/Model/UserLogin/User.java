package web.id.azammukhtar.subico.Model.UserLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("user")
    @Expose
    private DataUser user;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DataUser getUser() {
        return user;
    }

    public void setUser(DataUser user) {
        this.user = user;
    }
}
