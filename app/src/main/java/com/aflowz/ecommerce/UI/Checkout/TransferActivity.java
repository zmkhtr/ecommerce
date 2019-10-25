package com.aflowz.ecommerce.UI.Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.GlobalCheckOutResponse.CheckoutGlobalResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse.LocalCheckoutResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse.Product;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.MainActivity;
import com.aflowz.ecommerce.Utils.MainUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.CHECKOUT_KEY;
import static com.aflowz.ecommerce.Utils.Constant.DETAIL_CHECKOUT;
import static com.aflowz.ecommerce.Utils.Constant.GLOBAL;

public class TransferActivity extends BaseActivity {
    private static final String TAG = "TransferActivity";

    Product localCheckoutResponse;
    com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.GlobalCheckOutResponse.Product checkoutGlobalResponse;

    @BindView(R.id.textTransferTotalPrice)
    TextView mPrice;

    @BindView(R.id.textTransferUniqueCode)
    TextView mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String key = intent.getStringExtra(CHECKOUT_KEY);

        if (key.equals(GLOBAL)){
            checkoutGlobalResponse = intent.getExtras().getParcelable(DETAIL_CHECKOUT);
            Timber.d("Global %s", checkoutGlobalResponse);
            mPrice.setText(MainUtils.formatRupiah(checkoutGlobalResponse.getTotalPrice().toString()));
            mCode.setText(MainUtils.formatRupiah(checkoutGlobalResponse.getUniquePrice().toString()));
        } else {
            localCheckoutResponse = intent.getExtras().getParcelable(DETAIL_CHECKOUT);
            Timber.d("Local %s", localCheckoutResponse);
            String price = MainUtils.formatRupiah(localCheckoutResponse.getTotalPrice().toString());
            String code = MainUtils.formatRupiah(localCheckoutResponse.getUniquePrice().toString());
            Timber.d("Local %s", price);
            Timber.d("Local %s", code);
            mPrice.setText(price);
            mCode.setText(code);
        }


        setUpButton();
        if(getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setAppBarTitle(getString(R.string.local_checkout));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnTransferBack)
    void back(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}
