package com.aflowz.ecommerce.UI.History;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.OrderResponse;
import com.aflowz.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<OrderResponse.Order> orderResponses = new ArrayList<>();
    private OnItemClick listener;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderResponse.Order orderResponse = orderResponses.get(position);
        holder.mStatus.setText(orderResponse.getStatus());
        String code = "Order#" + orderResponse.getCode();
        holder.mCode.setText(code);
        holder.mDate.setText(orderResponse.getCreatedAt());
        

        holder.itemView.setOnClickListener(v -> listener.onItemClick(orderResponse));

    }

    void submitList(List<OrderResponse.Order> orderResponses) {
        List<OrderResponse.Order> oldList = this.orderResponses;
        DiffUtil.DiffResult diffUtil = DiffUtil.calculateDiff(new DataDiffUtilCallback(oldList,orderResponses));
        this.orderResponses = orderResponses;
        diffUtil.dispatchUpdatesTo(this);
    }

    void clearList(){
        this.orderResponses.clear();
    }

    public class DataDiffUtilCallback extends DiffUtil.Callback{
        List<OrderResponse.Order> oldItem;
        List<OrderResponse.Order> newItem;

        DataDiffUtilCallback(List<OrderResponse.Order> oldItem, List<OrderResponse.Order> newItem) {
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
            return oldItem.get(oldItemPosition).getId().equals(newItem.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldItem.get(oldItemPosition).equals(newItem.get(newItemPosition));
        }
    }

    @Override
    public int getItemCount() {
        return orderResponses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
        void onItemClick(OrderResponse.Order order);
    }

    void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
