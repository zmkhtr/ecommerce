
package web.id.azammukhtar.subico.Model.SubCategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCat {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("subCategories")
    @Expose
    private List<SubCategory> subCategories = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

}
