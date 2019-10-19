package com.aflowz.ecommerce.UI.ProductActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.ProductAdapter;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class ProductPagingAdapter extends PagedListAdapter<ProductListDetailData, ProductPagingAdapter.ViewHolder> {

    private Context context;
    private OnItemClick listener;

    ProductPagingAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.context = mCtx;
    }


    private static DiffUtil.ItemCallback<ProductListDetailData> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ProductListDetailData>() {
                @Override
                public boolean areItemsTheSame(@NonNull ProductListDetailData oldItem, @NonNull ProductListDetailData newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ProductListDetailData oldItem, @NonNull ProductListDetailData newItem) {
                    return oldItem.equals(newItem);
                }
            };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListDetailData productData = getItem(position);
        holder.mName.setText(productData.getName());
        Timber.d("MASASIH %s", productData.getName());
        //holder.mPrice.setText(MainUtils.formatRupiah(productData.getPrice()));
        holder.mPrice.setText(productData.getPrice());

        if (productData.getJenisOrder().equals("PEMBELIAN")) {
            holder.mPrice.setText(MainUtils.formatRupiah(productData.getPrice()));
        } else {
//            holder.mPrice.setText(MainUtils.cutPriceString(productData.getPrice()));
            holder.mPrice.setText(R.string.rent_price);
        }
        Glide.with(context)
                .load(IMG_URL + MainUtils.cutImageUrl(productData.getImages()))
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.mImage);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(productData));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageListMainLayout)
        ImageView mImage;
        @BindView(R.id.textListMainNameLayout)
        TextView mName;
        @BindView(R.id.textListMainPriceLayout)
        TextView mPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public interface OnItemClick {
        void onItemClick(ProductListDetailData productListData);
    }

    void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
