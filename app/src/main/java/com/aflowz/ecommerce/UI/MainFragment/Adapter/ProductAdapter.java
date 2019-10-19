package com.aflowz.ecommerce.UI.MainFragment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductListDetailData> productListData = new ArrayList<>();
    private Context context;
    private OnItemClick listener;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListDetailData productData = productListData.get(position);
        holder.mName.setText(productData.getName());
        holder.mPrice.setText(MainUtils.formatRupiah(productData.getPrice()));
        Glide.with(context)
                .load(IMG_URL + MainUtils.cutImageUrl(productData.getImages()))
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.mImage);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(productData));
    }

    @Override
    public int getItemCount() {
        return productListData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageHomeProduct)
        ImageView mImage;
        @BindView(R.id.textHomeProductName)
        TextView mName;
        @BindView(R.id.textHomeProductPrice)
        TextView mPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setProductListData(List<ProductListDetailData> productListData) {
        this.productListData = productListData;
    }

    public interface OnItemClick {
        void onItemClick(ProductListDetailData productListData);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
