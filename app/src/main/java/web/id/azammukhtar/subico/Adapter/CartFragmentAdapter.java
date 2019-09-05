package web.id.azammukhtar.subico.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import web.id.azammukhtar.subico.Model.Cart.Datum;
import web.id.azammukhtar.subico.Model.ProductModel;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;

import static web.id.azammukhtar.subico.Utils.Constant.IMG_URL;

public class CartFragmentAdapter extends RecyclerView.Adapter<CartFragmentAdapter.ViewHolder> {
    private static final String TAG = "HomeFragmentAdapter";

    private List<Datum> productModels = new ArrayList<>();
    private OnItemClick listener;
    private Context context;

    public CartFragmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum productModel = productModels.get(position);
        holder.productName.setText(productModel.getName());
        holder.productPrice.setText("Rp " + productModel.getPrice());
        holder.productColor.setText("Color " + productModel.getColor());
        holder.productSize.setText("Size " + productModel.getSize());

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
        holder.compatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRemoveClick(productModel.getCartId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public void setProductModels(List<Datum> productModels) {
        this.productModels = productModels;
        notifyDataSetChanged();
    }

    public void clearList(List<Datum> productModels) {
        this.productModels = productModels;
        productModels.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productSize, productColor;
        AppCompatButton compatButton;
        ImageView productImage;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textListMainName);
            productPrice = itemView.findViewById(R.id.textListMainPrice);
            productImage = itemView.findViewById(R.id.imageListMain);
            productSize = itemView.findViewById(R.id.textListMainSize);
            productColor = itemView.findViewById(R.id.textListMainColor);
            compatButton = itemView.findViewById(R.id.buttonListRemoveItem);
            itemView.setOnClickListener(v -> {
                listener.onItemClick(getAdapterPosition());
                Log.d(TAG, "ViewHolder: clicked");
            });
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
        void onRemoveClick(String cartId);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
