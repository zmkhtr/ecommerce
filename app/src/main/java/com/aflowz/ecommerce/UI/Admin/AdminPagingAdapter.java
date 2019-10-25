package com.aflowz.ecommerce.UI.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.Datum;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class AdminPagingAdapter extends PagedListAdapter<Datum, AdminPagingAdapter.ViewHolder> {

    private Context context;
    private OnItemClick listener;

    AdminPagingAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.context = mCtx;
    }


    private static DiffUtil.ItemCallback<Datum> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Datum>() {
                @Override
                public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
                    return oldItem.equals(newItem);
                }
            };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum orderResponse = getItem(position);
        holder.mStatus.setText(orderResponse.getStatus());
        String code = "Order#" + orderResponse.getCode();
        holder.mCode.setText(code);
        holder.mDate.setText(orderResponse.getCreatedAt());


        holder.itemView.setOnClickListener(v -> listener.onItemClick(orderResponse));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textListHistoryCode)
        TextView mCode;
        @BindView(R.id.textListHistoryDate)
        TextView mDate;
        @BindView(R.id.textListHistoryStatus)
        TextView mStatus;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
    public interface OnItemClick {
        void onItemClick(Datum datum);
    }

    void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
