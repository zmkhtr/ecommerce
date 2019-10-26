package com.aflowz.ecommerce.UI.ProductActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCategory.CategoryData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductList.ProductListDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseSubCategory.SubCategoryData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.CartActivity.CartActivity;
import com.aflowz.ecommerce.UI.DetailActivity.DetailActivity;
import com.aflowz.ecommerce.UI.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;

public class ProductActivity extends BaseActivity {
    public static final String PRODUCT_KEY = "PRODUCT_KEY";
    public static final String CATEGORY_KEY = "CATEGORY_KEY";
    public static final String SUB_CATEGORY_KEY = "SUB_CATEGORY_KEY";
    public static final String QUERY_KEY = "QUERY";

    TextView textCartItemCount;
    int mCartItemCount;

    ProductPagingAdapter productAdapter;
    ProductViewModel productViewModel;

    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dropdownSearchKategori)
    AutoCompleteTextView mCategory;

    @BindView(R.id.dropdownSearchSort)
    AutoCompleteTextView mSort;

    @BindView(R.id.dropdownSearchSubKategori)
    AutoCompleteTextView mSubCategory;

    EditText mSearch;
    String productKey, query, categoryKey, subCategory, sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);

        Intent intentKey = getIntent();

        productKey = intentKey.getStringExtra(PRODUCT_KEY);
        query = intentKey.getStringExtra(QUERY_KEY);
        categoryKey = intentKey.getStringExtra(CATEGORY_KEY);
        subCategory = intentKey.getStringExtra(SUB_CATEGORY_KEY);

        Timber.d("Filter query %s", query);
//        getSellProduct();
        decideList();
        setFilter();

        toolbar.setElevation(0);
        mSearch = toolbar.findViewById(R.id.edtSearchMainMenu);
        mSearch.setText(query);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            Timber.d("onEditorAction: pressed ");
            query = mSearch.getText().toString();
            decideList();
            return true;
        });

    }

    private void setFilter() {
        List<CategoryData> categoryData = AppDatabase.getCategory();

        List<String> kategoriList = new ArrayList<>();

        for (int i = 0; i < categoryData.size(); i++) {
            kategoriList.add(categoryData.get(i).getName());
        }

        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        kategoriList);


        mCategory.setText(categoryKey);
        mCategory.setAdapter(adapterSize);

        mCategory.setOnItemClickListener((adapterView, view, i, l) -> {
            categoryKey = mCategory.getText().toString();
            decideList();
        });

        List<SubCategoryData> subCategoryData = AppDatabase.getSubCategory();

        List<String> subKategoriList = new ArrayList<>();
        for (int i = 0; i < subCategoryData.size(); i++) {
            subKategoriList.add(subCategoryData.get(i).getName());
        }

        ArrayAdapter<String> adapterSize1 =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        subKategoriList);
//
        mSubCategory.setText(subCategory);
        mSubCategory.setAdapter(adapterSize1);

        mSubCategory.setOnItemClickListener((adapterView, view, i, l) -> {
            subCategory = mSubCategory.getText().toString();
            decideList();
        });

        List<String> price = new ArrayList<>();

        price.add("price-asc");
        price.add("price-desc");
        ArrayAdapter<String> adapterSize2 =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        price);

        mSort.setText("");
        mSort.setAdapter(adapterSize2);

        mSort.setOnItemClickListener((adapterView, view, i, l) -> {
            sort = mSort.getText().toString();
            decideList();
        });

//        if (productKey != null) {
//            if (productKey.equals("SALE")) {
//                getSellProduct(mSearch.getText().toString(),mSubCategory.getText().toString(),mCategory.getText().toString(),mSort.getText().toString(),"");
//            } else if (productKey.equals("RENT")) {
//                getRentProduct(mSearch.getText().toString(),mSubCategory.getText().toString(),mCategory.getText().toString(),mSort.getText().toString(),"/sewa");
//            }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_cart);
        // Inflate the menu; this adds items to the action bar if it is present.
        View actionView = menuItem.getActionView();
        textCartItemCount = actionView.findViewById(R.id.cart_badge);

        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        setupBadge();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {
        mCartItemCount = AppDatabase.getCart().size();

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void decideList() {
        Timber.d("ProductDetailRentData key %s", productKey);
        if (productKey != null) {
            if (productKey.equals("SALE")) {
                getSellProduct(query,subCategory,categoryKey,sort);
            } else if (productKey.equals("RENT")) {
                getRentProduct(query,subCategory,categoryKey,sort);
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

        recyclerView.getRecycledViewPool().clear();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductPagingAdapter(this);
        recyclerView.setAdapter(productAdapter);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.pagedListLiveData.observe(this, productListDetailData -> productAdapter.submitList(productListDetailData));
        if (productAdapter.getItemCount() > 0){
            Timber.d("Filter not null" );
            productViewModel.refresh();
        }
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(productListData -> {
            Timber.d("product clicked%s", productListData);
            Intent intent = new Intent(this, DetailActivity.class);
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

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductPagingAdapter(this);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.pagedListLiveData.observe(this, productListDetailData -> productAdapter.submitList(productListDetailData));
        Timber.d("Filter item count %s", productAdapter.getItemCount());
        if (productAdapter.getItemCount() > 0){
            Timber.d("Filter not null" );
            productViewModel.refresh();
        }
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(productListData -> {
            Timber.d("product clicked%s", productListData);
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(PRODUCT_ID, productListData);
            intent.putExtra(PRODUCT_KEY, productKey);
            startActivity(intent);
        });
    }
}
