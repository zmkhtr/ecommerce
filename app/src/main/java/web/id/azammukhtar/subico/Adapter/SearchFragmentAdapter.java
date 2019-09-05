package web.id.azammukhtar.subico.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import web.id.azammukhtar.subico.Model.Product.Datum;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;

import static web.id.azammukhtar.subico.Utils.Constant.IMG_URL;

public class SearchFragmentAdapter extends RecyclerView.Adapter<SearchFragmentAdapter.ViewHolder> implements Filterable {
    private static final String TAG = "HomeFragmentAdapter";

    private List<Datum> productModels = new ArrayList<>();
    private List<Datum> productModelsFull = new ArrayList<>();
    private OnItemClick listener;
    private Context context;

    public SearchFragmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum productModel = productModels.get(position);
        holder.productName.setText(productModel.getName());
        String price = "Rp " + productModel.getPrice();
        String urlLast = null;
//        Uri uri = Uri.fromFile(new File(productModel.getImages()));
        holder.productPrice.setText(price);
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
        return productModels == null ? 0 : productModels.size();
    }

    public void setProductModels(List<Datum> productModels1) {
        this.productModels = productModels1;
        notifyDataSetChanged();
    }

    public void clearList(List<Datum> productModels) {
        this.productModels = productModels;
        productModels.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.textListMainName);
            productPrice = itemView.findViewById(R.id.textListMainPrice);
            productImage = itemView.findViewById(R.id.imageListMain);
            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
        }
    }

//    public int getLastVisibleItemId() {
//        if (productModels.isEmpty()) {
//            return 0;
//        }
//        return productModels.get(productModels.size() - 1).getId();
//    }


    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }


    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Datum> filteredList = new ArrayList<>();
            Log.d(TAG, "performFiltering: init " + charSequence);
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(productModelsFull);
                Log.d(TAG, "performFiltering: null ");
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                Log.d(TAG, "performFiltering: not null ");
                for (Datum item : productModelsFull) {
                    String key = filterPattern.substring(0, 3);
                    String filter = filterPattern.replace(key, "");
                    Log.d(TAG, "performFiltering: " + key + " filtered " + filter);

                    switch (key) {
                        case "sub":
                            for (int i = 0; i < item.getSubCategory().size(); i++) {
                                if (item.getSubCategory().get(i).getName().toLowerCase().contains(filter)) {
                                    Log.d(TAG, "performFiltering: " + item.getSubCategory().get(i).getName().toLowerCase());
                                    filteredList.add(item);
                                }
                            }
                            break;
                        case "cat":
                            for (int i = 0; i < item.getSubCategory().size(); i++) {
                                if (item.getSubCategory().get(i).getCategory().getName().toLowerCase().contains(filter)) {
                                    Log.d(TAG, "performFiltering: " + item.getSubCategory().get(i).getCategory().getName().toLowerCase());
                                    filteredList.add(item);
                                }
                            }
                            break;
                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productModels.clear();
//            productModelsFull.clear();
            productModels.addAll((List) filterResults.values);
            Log.d(TAG, "publishResults: " + filterResults.values);
            notifyDataSetChanged();
//            int count = getItemCount();
//            notifyItemRangeInserted(count, filterResults.count);
        }
    };

    public List<Datum> getProductModels() {
        return productModels;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
