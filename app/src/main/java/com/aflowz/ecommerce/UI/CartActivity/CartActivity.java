package com.aflowz.ecommerce.UI.CartActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.Checkout.ChooseCheckoutActivity;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class CartActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, CartContract.View {

    @BindView(R.id.recyclerViewCart)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshCart)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.textCartEmptyItem)
    TextView textCartEmptyItem;
    @BindView(R.id.buttonCheckOut)
    Button mCheckout;

    private CartAdapter cartAdapter;

    private CartContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        cartAdapter = new CartAdapter(this);

        swipeRefreshLayout.setOnRefreshListener(this);

        checkIfEmpty();
        setRecyclerView();
        setUpButton();
//        calculatePrice();
        setAppBarTitle(getString(R.string.appbar_title));
        setOnClick();
        presenter = new CartPresenter(this, new CartRepository());
        calculatePrice();
    }

    private void setOnClick(){
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Timber.d("item click");
            }

            @Override
            public void onRemoveClick(CartDetailData cartDetailData) {
                Timber.d("item Remove");
                presenter.removeCart(cartDetailData.getCartId(), cartDetailData);
                checkIfEmpty();
            }

            @Override
            public void onBindViewHolder(CartAdapter.ViewHolder holder) {
                Timber.d("item OnBindViewHolder");
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkIfEmpty();
    }

    private void checkIfEmpty() {
        if (AppDatabase.getCart().isEmpty()) {
            textCartEmptyItem.setVisibility(View.VISIBLE);
        } else {
            textCartEmptyItem.setVisibility(View.INVISIBLE);
        }
    }

    private void setRecyclerView() {
        cartAdapter.submitList(AppDatabase.getCart());
        Timber.d("Cart adapter %s", cartAdapter.getItemCount());
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManagerProduct.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(cartAdapter);
    }

    @OnClick(R.id.buttonCheckOut)
    void checkOut() {
        if(AppDatabase.getCart().isEmpty()){
            Toast.makeText(this, R.string.empty_cart_toast, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, ChooseCheckoutActivity.class));
        }
    }

    @Override
    public void onRefresh() {
        setRecyclerView();
        new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
    }

    private void calculatePrice() {
        int totalPrice = 0;
        List<String> cartDetailData = new ArrayList<>();

        String price, priceSecond;
        int quantity = 0;
//        for (int i = 0; i < cartAdapter.getCartDetailDataList().size(); i++) {
//            Timber.d("Test data asdas %s", cartAdapter.getCartDetailDataList().get(i).getPrice());
//            cartDetailData.set(i, cartAdapter.getCartDetailDataList().get(i).getPrice());
//        }
        Timber.d("Test data asdasd %s", cartAdapter.getCartDetailDataList().size() );

        for (int i = 0; i < cartAdapter.getCartDetailDataList().size(); i++) {
            if (cartAdapter.getCartDetailDataList().get(i).getPrice().contains("Hari")){
                Timber.d("Test data dalem %s", cartAdapter.getCartDetailDataList().get(i));
                price = MainUtils.cutPriceCart(cartAdapter.getCartDetailDataList().get(i).getPrice());
                price = price.replace(" ", "");
                quantity = cartAdapter.getCartDetailDataList().get(i).getQty();
                quantity = Integer.parseInt(price) * quantity;
                cartDetailData.add(String.valueOf(quantity));
                Timber.d("Test data dalem harga %s", price);
            } else {
                Timber.d("Test data dalem not %s", cartAdapter.getCartDetailDataList().get(i));
                priceSecond = MainUtils.cutPrice(cartAdapter.getCartDetailDataList().get(i).getPrice());
                priceSecond = priceSecond.replace(" ", "");
                quantity = cartAdapter.getCartDetailDataList().get(i).getQty();
                quantity = Integer.parseInt(priceSecond) * quantity;
                cartDetailData.add(String.valueOf(quantity));
                Timber.d("Test data dalem harga not %s", priceSecond);
            }

            totalPrice += Integer.parseInt(cartDetailData.get(i));
        }

        String totalPriceLabel = "Total : " + MainUtils.formatRupiah(String.valueOf(totalPrice));
        SessionManager.getInstance().setPrice(String.valueOf(totalPrice));
        Timber.d("harga cuy %s", SessionManager.getInstance().getPrice());
        mCheckout.setText(totalPriceLabel);
    }

    @Override
    public void openNewActivity() {

    }

    @Override
    public void showSuccessMessage(String message, CartDetailData cartDetailData) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        AppDatabase.deleteCart(cartDetailData);
        onRefresh();
        calculatePrice();
    }

    @Override
    public void showErrorMessage(String message, Throwable e) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showDialogLoading("Removing item...");
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }
}
