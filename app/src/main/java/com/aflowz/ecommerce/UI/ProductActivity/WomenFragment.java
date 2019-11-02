package com.aflowz.ecommerce.UI.ProductActivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.DetailActivity.DetailActivity;

import timber.log.Timber;

import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.PRODUCT_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.categoryKey;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.productKey;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.sort;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.subCategory;
import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class WomenFragment extends Fragment {



    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_women, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        decideList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void decideList() {
        Timber.d("ProductDetailRentData key %s", productKey);
        if (productKey != null) {
            if (productKey.equals("SALE")) {
                getSellProduct(ProductActivity.query,subCategory,categoryKey,sort);
            } else if (productKey.equals("RENT")) {
                getRentProduct(ProductActivity.query,subCategory,categoryKey,sort);
            }
        }
    }

    private void getRentProduct(String keyWord, String subCategory, String category, String sort) {
        Timber.d("Filter : " + keyWord + " " + subCategory + " " + category + " " + sort);
        ProductDataSource.KEYWORD = keyWord;
        ProductDataSource.SUB_CATEGORY = subCategory;
        ProductDataSource.CATEGORY = category;
        ProductDataSource.SORT = sort;
        ProductDataSource.RENT = true;

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProductWomen);
        ProductPagingAdapter productAdapter;
        ProductViewModel productViewModel;
        recyclerView.getRecycledViewPool().clear();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductPagingAdapter(getActivity());
        recyclerView.setAdapter(productAdapter);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.pagedListLiveData.observe(this, productAdapter::submitList);
        if (productAdapter.getItemCount() > 0){
            Timber.d("Filter not null" );
            productViewModel.refresh();
        }
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(productListData -> {
            Timber.d("product clicked%s", productListData);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(PRODUCT_ID, productListData);
            intent.putExtra(PRODUCT_KEY, productKey);
            startActivity(intent);
        });
    }

    private void getSellProduct(String keyWord, String subCategory, String category, String sort) {
        Timber.d("Filter : " + keyWord + " " + subCategory + " " + category + " " + sort);
        ProductDataSource.KEYWORD = keyWord;
        ProductDataSource.SUB_CATEGORY = subCategory;
        ProductDataSource.CATEGORY = category;
        ProductDataSource.SORT = sort;
        ProductDataSource.RENT = false;

        ProductPagingAdapter productAdapter;
        ProductViewModel productViewModel;
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewProductMen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductPagingAdapter(getActivity());
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.pagedListLiveData.observe(this, productAdapter::submitList);
        Timber.d("Filter item count %s", productAdapter.getItemCount());
        if (productAdapter.getItemCount() > 0){
            Timber.d("Filter not null" );
            productViewModel.refresh();
        }
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(productListData -> {
            Timber.d("product clicked%s", productListData);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(PRODUCT_ID, productListData);
            intent.putExtra(PRODUCT_KEY, productKey);
            startActivity(intent);
        });
    }

}
