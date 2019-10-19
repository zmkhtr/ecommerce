package com.aflowz.ecommerce.UI.MainFragment.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory.SubCategoryData;
import com.aflowz.ecommerce.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {

    private List<SubCategoryData> categoryDataList = new ArrayList<>();
    private OnItemClick listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SubCategoryData categoryData = categoryDataList.get(position);
        holder.mTitle.setText(categoryData.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(categoryData));
    }

    @Override
    public int getItemCount() {
        return categoryDataList.size();
    }

    public void setCategoryDataList(List<SubCategoryData> categoryDataList) {
        this.categoryDataList = categoryDataList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textItemCategory)
        TextView mTitle;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClick {
        void onItemClick(SubCategoryData subCategoryData);
    }

    public void setOnItemClickListener(OnItemClick listener) {
        this.listener = listener;
    }
}
