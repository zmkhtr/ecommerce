package web.id.azammukhtar.subico.Adapter;

import android.content.Context;
import android.util.Log;
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

import web.id.azammukhtar.subico.Model.Favorite.Datum;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;

import static web.id.azammukhtar.subico.Utils.Constant.IMG_URL;

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolder> {
    private static final String TAG = "HomeFragmentAdapter";

    private List<Datum> productModels = new ArrayList<>();
    private OnItemClick listener;
    private Context context;

    public FavoriteFragmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_favorite  , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum productModel = productModels.get(position);
        holder.productName.setText(productModel.getName());
        holder.productPrice.setText(productModel.getPrice());
        Log.d(TAG, "onBindViewHolder: " + productModel.getName());
        String urlLast = null;
        if (productModel.getImages() != null) {
            String url = productModel.getImages();
            String urlFirst = url.replace("[", "");
            String urlSecond = urlFirst.replace("\\", "");
            String urlEnd = urlSecond.replace("\"", "");
            urlLast = urlEnd.substring(0, urlEnd.length() - 1);
        }

        if (!ApiNetwork.isNetworkConnected(context)) {
            Glide.with(context).load(IMG_URL + urlLast).placeholder(R.drawable.ic_broken_image).into(holder.productImage);
            Log.d(TAG, "onBindViewHolder: gg " + productModel.getImages());
        }
        Log.d(TAG, "onBindViewHolder: yy " + productModel.getImages());
        Glide.with(context)
                .load(IMG_URL + urlLast)
                .placeholder(R.drawable.ic_broken_image)
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public void setProductModels(List<Datum> productModels1) {
//        int count = getItemCount();
//        productModels.addAll(productModels1);
//        notifyItemRangeInserted(count, productModels1.size());

        this.productModels = productModels1;
        notifyDataSetChanged();
    }

    public void clearList(List<Datum> datumList) {
        this.productModels = datumList;
        datumList.clear();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textListMainName);
            productPrice = itemView.findViewById(R.id.textListMainPrice);
            productImage = itemView.findViewById(R.id.imageListMain);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
            });
        }
    }

    public List<Datum> getProductModels() {
        return productModels;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
