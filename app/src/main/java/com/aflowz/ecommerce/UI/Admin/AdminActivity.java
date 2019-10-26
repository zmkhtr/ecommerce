package com.aflowz.ecommerce.UI.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseAllOrder.Datum;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.History.DetailHistoryActivity;
import com.aflowz.ecommerce.UI.ProductActivity.ProductPagingAdapter;
import com.aflowz.ecommerce.UI.ProductActivity.ProductViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.ADMIN_KEY;
import static com.aflowz.ecommerce.Utils.Constant.ORDER_ID;

public class AdminActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerViewAdmin)
    RecyclerView recyclerView;

    AdminPagingAdapter adminPagingAdapter;
    AdminViewModel adminViewModel;

    @BindView(R.id.swipeRefreshHistoryAdmin)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);
        setRecyclerView();
        setOnClick();
        setUpButton();
        setAppBarTitle("Admin - All Transaction");
    }

    private void setOnClick(){
        adminPagingAdapter.setOnItemClickListener(datum -> {
            Intent intent = new Intent(getApplicationContext(), DetailHistoryActivity.class);
            intent.putExtra(ADMIN_KEY, true);
            intent.putExtra(ORDER_ID, datum.getId());
            startActivity(intent);
        });
    }



    private void setRecyclerView(){
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(this);
        layoutManagerProduct.setStackFromEnd(true);
        layoutManagerProduct.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManagerProduct.getOrientation());
        adminPagingAdapter = new AdminPagingAdapter(this);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adminPagingAdapter);
        recyclerView.smoothScrollToPosition(adminPagingAdapter.getItemCount());
        adminViewModel = ViewModelProviders.of(this).get(AdminViewModel.class);
        adminViewModel.pagedListLiveData.observe(this, productListDetailData -> {
            adminPagingAdapter.submitList(productListDetailData);
            swipeRefreshLayout.setRefreshing(false);
        });
        recyclerView.setAdapter(adminPagingAdapter);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        adminViewModel.refresh();
        Timber.d("onRefresh called");
    }
}
