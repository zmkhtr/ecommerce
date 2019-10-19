package com.aflowz.ecommerce.UI.FavoriteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.ProductAdapter;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.SliderMiniAdapter;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteDetailData> favoriteDetailDataList = new ArrayList<>();
    private Context context;
    private OnItemClick listener;

    FavoriteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_favorite, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavoriteDetailData favoriteDetailData = favoriteDetailDataList.get(position);
        holder.mItemName.setText(favoriteDetailData.getName());
        holder.mItemPrice.setText(favoriteDetailData.getPrice());


        if (favoriteDetailData.getJenisOrder().equals("PEMBELIAN")) {
            holder.mItemPrice.setText(MainUtils.formatRupiah(favoriteDetailData.getPrice()));
        } else {
            holder.mItemPrice.setText(R.string.rent_price);
        }
        Glide.with(context)
                .load(IMG_URL + MainUtils.cutImageUrl(favoriteDetailData.getImages()))
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.mImage);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(favoriteDetailData));

    }

    void submitList(List<FavoriteDetailData> favoriteDetailData) {
        List<FavoriteDetailData> oldList = this.favoriteDetailDataList;
        DiffUtil.DiffResult diffUtil = DiffUtil.calculateDiff(new DataDiffUtilCallback(oldList,favoriteDetailData));
        this.favoriteDetailDataList = favoriteDetailData;
        diffUtil.dispatchUpdatesTo(this);
    }

    void clearList(){
        this.favoriteDetailDataList.clear();
    }

    public class DataDiffUtilCallback extends DiffUtil.Callback{
        List<FavoriteDetailData> oldItem;
        List<FavoriteDetailData> newItem;

        DataDiffUtilCallback(List<FavoriteDetailData> oldItem, List<FavoriteDetailData> newItem) {
            this.oldItem = oldItem;
            this.newItem = newItem;
        }

        @Override
        public int getOldListSize() {
            return oldItem.size();
        }

        @Override
        public int getNewListSize() {
            return newItem.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItem.get(oldItemPosition).getId() == newItem.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItem.get(oldItemPosition).equals(newItem.get(newItemPosition));
        }
    }

    @Override
    public int getItemCount() {
        return favoriteDetailDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageListMain)
        ImageView mImage;
        @BindView(R.id.textListMainName)
        TextView mItemName;
        @BindView(R.id.textListMainPrice)
        TextView mItemPrice;
        @BindView(R.id.imageListFavoriteIcon)
        ImageView mFav;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onItemClick(FavoriteDetailData favoriteDetailData);
    }

    void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
