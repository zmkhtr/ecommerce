package web.id.azammukhtar.subico.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import web.id.azammukhtar.subico.Model.Category.Product;
import web.id.azammukhtar.subico.Model.SubCategory.SubCategory;
import web.id.azammukhtar.subico.R;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private List<SubCategory> titles = new ArrayList<>();
    private OnItemClick listener;


    public List<SubCategory> getTitles() {
        return titles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategory title = titles.get(position);
        holder.textView.setText(title.getName());
    }

    public void setTitles(List<SubCategory> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewListCategory);
            textView = itemView.findViewById(R.id.textListCategory);
            cardView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
        }
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
