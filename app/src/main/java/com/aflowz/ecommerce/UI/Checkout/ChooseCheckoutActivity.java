package com.aflowz.ecommerce.UI.Checkout;

import android.content.Intent;
import android.os.Bundle;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCheckoutActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_checkout);

        ButterKnife.bind(this);

        setUpButton();
        setAppBarTitle(getString(R.string.choose_checkout));
    }

    @OnClick(R.id.btnChooseLocalCheckout)
    void localCheckout(){
        startActivity(new Intent (this, CheckOutActivity.class));
    }
    @OnClick(R.id.btnChooseGlobalCheckout)
    void globalCheckout(){
        startActivity(new Intent (this, CheckOutGlobalActivity.class));
    }

}
