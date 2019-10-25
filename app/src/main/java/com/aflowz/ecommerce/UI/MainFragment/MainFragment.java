package com.aflowz.ecommerce.UI.MainFragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseFragment;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.DetailActivity.DetailActivity;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.ProductAdapter;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.SliderMiniAdapter;
import com.aflowz.ecommerce.UI.MainFragment.Adapter.SubCategoryAdapter;
import com.aflowz.ecommerce.UI.ProductActivity.ProductActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.CATEGORY_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.PRODUCT_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.QUERY_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.SUB_CATEGORY_KEY;
import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    private SubCategoryAdapter subCategoryAdapter;
    private SliderMiniAdapter sliderMiniAdapter;
    private ProductAdapter productAdapter;

//    @BindView(R.id.shimmerMainLoading)
//    ShimmerFrameLayout shimmerFrameLayout;

    @BindView(R.id.recyclerViewHomeSubCategories)
    RecyclerView recyclerViewCategories;

    @BindView(R.id.recyclerViewHomeProduct)
    RecyclerView recyclerViewProduct;

    @BindView(R.id.sliderHomeImage)
    SliderView mSlider;

    @BindView(R.id.card)
    CardView mCard;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* INIT ADAPTER */
        subCategoryAdapter = new SubCategoryAdapter();
        sliderMiniAdapter = new SliderMiniAdapter(getContext());
        productAdapter = new ProductAdapter(getContext());
        
        setRecyclerView();
        setRecyclerOnClick();
        setSlider();

//        if (AppDatabase.getProduct().isEmpty()) {
//            mCard.setVisibility(View.INVISIBLE);
//            shimmerFrameLayout.startShimmerAnimation();
//        } else if (!AppDatabase.getProduct().isEmpty()){
//            mCard.setVisibility(View.VISIBLE);
//            shimmerFrameLayout.stopShimmerAnimation();
//            shimmerFrameLayout.setVisibility(View.GONE);
//        }
    }

    private void setRecyclerOnClick(){
        subCategoryAdapter.setOnItemClickListener(v -> {
            Timber.d("subcategory clicked%s", v);
            Timber.d("product clicked%s", v);
            Intent intent = new Intent(getContext(), ProductActivity.class);
            intent.putExtra(PRODUCT_ID, v.getId());
            intent.putExtra(PRODUCT_KEY, "SALE");
            intent.putExtra(CATEGORY_KEY, "");
            intent.putExtra(SUB_CATEGORY_KEY, v.getName());
            intent.putExtra(QUERY_KEY, "");
            startActivity(intent);
        });
        productAdapter.setOnItemClickListener(v -> {
            Timber.d("product clicked %s", v);
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtra(PRODUCT_ID, v.getId());
            intent.putExtra(PRODUCT_KEY, "SALE");
            intent.putExtra(CATEGORY_KEY, "");
            intent.putExtra(SUB_CATEGORY_KEY, "");
            intent.putExtra(QUERY_KEY, "");
            startActivity(intent);
        });
    }

    private void setRecyclerView() {
        /* SET SUB CATEGORY DATA */
        subCategoryAdapter.setCategoryDataList(AppDatabase.getSubCategory());
        LinearLayoutManager layoutManagerSubCategory = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategories.setLayoutManager(layoutManagerSubCategory);
        recyclerViewCategories.setHasFixedSize(true);
        recyclerViewCategories.setAdapter(subCategoryAdapter);

        /* SET PRODUCT DATA */
        productAdapter.setProductListData(AppDatabase.getProduct());
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewProduct.setLayoutManager(layoutManagerProduct);
        recyclerViewProduct.setHasFixedSize(true);
        recyclerViewProduct.setAdapter(productAdapter);
    }

    private void setSlider(){
        List<String> listImageBanner = new ArrayList<>();

        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner1410873492541838945caed.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner_(580x240)_-_promo_FREE_UPSIZE_LED_TV.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner_Promo_Ramadhan.jpg");

        mSlider.setSliderAdapter(sliderMiniAdapter);
        sliderMiniAdapter.setImageUrl(listImageBanner);
        sliderMiniAdapter.notifyDataSetChanged();
        mSlider.startAutoCycle();
        mSlider.setIndicatorAnimation(IndicatorAnimations.WORM);
        mSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    @OnClick(R.id.textHomeSell)
    void onSellClick() {
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra(PRODUCT_KEY, "SALE");
        startActivity(intent);
    }

    @OnClick(R.id.textHomeRent)
    void onRentClick() {
        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra(PRODUCT_KEY, "RENT");
        startActivity(intent);
    }

    @OnClick(R.id.textHomePreOrder)
    void onPreOrderClick() {
        Toast.makeText(getContext(), "Not implemented yet", Toast.LENGTH_SHORT).show();
    }

}
