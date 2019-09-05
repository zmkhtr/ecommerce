package web.id.azammukhtar.subico.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import web.id.azammukhtar.subico.Model.ProductModel;
import web.id.azammukhtar.subico.Model.TransactionModel;
import web.id.azammukhtar.subico.R;

public class TransactionFragmentAdapter extends RecyclerView.Adapter<TransactionFragmentAdapter.ViewHolder> {
    private static final String TAG = "HomeFragmentAdapter";

    private List<TransactionModel> productModels = new ArrayList<>();
    private OnItemClick listener;
    private Context context;

    public TransactionFragmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_transaction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TransactionModel productModel = productModels.get(position);
        holder.productName.setText(productModel.getOrderId());
        holder.productPrice.setText(productModel.getOrderStatus());
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }


    public void setProductModels(List<TransactionModel> productModels) {
        this.productModels = productModels;
    }

    public void clearList(List<TransactionModel> productModels) {
        this.productModels = productModels;
        productModels.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textListMainName);
            productPrice = itemView.findViewById(R.id.textListMainPrice);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
            });
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
