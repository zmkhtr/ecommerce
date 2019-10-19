package com.aflowz.ecommerce.UI.CartActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartDetailData> cartDetailDataList = new ArrayList<>();
    private Context context;
    private OnItemClick listener;

    CartAdapter(Context context) {
        this.context = context;
    }

    void submitList(List<CartDetailData> cartDetailDataList) {
        List<CartDetailData> oldList = this.cartDetailDataList;
        DiffUtil.DiffResult diffUtil = DiffUtil.calculateDiff(new DataDiffUtilCallback(oldList,cartDetailDataList));
        this.cartDetailDataList = cartDetailDataList;
        diffUtil.dispatchUpdatesTo(this);
    }

    public List<CartDetailData> getCartDetailDataList() {
        return cartDetailDataList;
    }

    public class DataDiffUtilCallback extends DiffUtil.Callback{
        List<CartDetailData> oldItem;
        List<CartDetailData> newItem;

        DataDiffUtilCallback(List<CartDetailData> oldItem, List<CartDetailData> newItem) {
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
            return oldItem.get(oldItemPosition).getCartId() == newItem.get(newItemPosition).getCartId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItem.get(oldItemPosition).equals(newItem.get(newItemPosition));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartDetailData cartDetailData = cartDetailDataList.get(position);
        String nameQuantity = cartDetailData.getName() + ". x " + cartDetailData.getQty();
        holder.mItemName.setText(nameQuantity);

        Timber.d("Harga %s", cartDetailData.getPrice());
        String price;
        if (cartDetailData.getPrice().contains("Hari")){
            String subString = cartDetailData.getPrice().substring(0,7);
            price = subString + " " + MainUtils.cutPriceString(cartDetailData.getPrice());
        } else {
            price =cartDetailData.getPrice();
        }

        String size = "Size : " + cartDetailData.getSize();
        holder.mItemSize.setText(size);
        holder.mItemPrice.setText(price);
        holder.mItemColor.setText(cartDetailData.getColor());
        holder.mRemove.setOnClickListener(v -> listener.onRemoveClick(cartDetailData));

        listener.onBindViewHolder(holder);

        Glide.with(context)
                .load(IMG_URL + MainUtils.cutImageUrl(cartDetailData.getImages()))
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.mImage);
    }







    @Override
    public int getItemCount() {
        return cartDetailDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageListMain)
        ImageView mImage;
        @BindView(R.id.textListMainName)
        TextView mItemName;
        @BindView(R.id.textListMainPrice)
        TextView mItemPrice;
        @BindView(R.id.textListMainSize)
        TextView mItemSize;
        @BindView(R.id.textListMainColor)
        TextView mItemColor;
        @BindView(R.id.textListMainDay)
        TextView mItemRentDay;
        @BindView(R.id.buttonListRemoveItem)
        Button mRemove;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> listener.onItemClick(getLayoutPosition()));
            mItemRentDay.setVisibility(View.GONE);
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
        void onRemoveClick(CartDetailData cartDetailData);
        void onBindViewHolder(ViewHolder holder);
    }

    void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
