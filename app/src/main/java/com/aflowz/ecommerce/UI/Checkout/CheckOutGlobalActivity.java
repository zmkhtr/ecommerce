package com.aflowz.ecommerce.UI.Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.GlobalCheckOutResponse.CheckoutGlobalResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.GlobalCheckOutResponse.Product;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aflowz.ecommerce.Utils.Constant.CHECKOUT_KEY;
import static com.aflowz.ecommerce.Utils.Constant.DETAIL_CHECKOUT;
import static com.aflowz.ecommerce.Utils.Constant.GLOBAL;

public class CheckOutGlobalActivity extends BaseActivity {

    @BindView(R.id.edtGlobalName)
    EditText mName;
    @BindView(R.id.edtGlobalPhone)
    EditText mPhone;
    @BindView(R.id.edtGlobalAddress)
    EditText mAddress;
    @BindView(R.id.edtGlobalCity)
    EditText mCity;
    @BindView(R.id.edtGlobalCountry)
    EditText mCountry;
    @BindView(R.id.edtGlobalPostalCode)
    EditText mPostalCode;
    @BindView(R.id.edtGlobalProvince)
    EditText mProvince;

    @BindView(R.id.textGlobalTotal)
    TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_global);
        ButterKnife.bind(this);



        setUpButton();
        setAppBarTitle(getString(R.string.global_checkout));


        ProfileUserData profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
        mName.setText(profileUserData.getName());
        mPhone.setText(profileUserData.getPhone());
        mAddress.setText(profileUserData.getAddress());
        String total = "Total price " + MainUtils.formatRupiah(SessionManager.getInstance().getPrice());
        mTotal.setText(total);
    }

    @OnClick(R.id.btnGlobalCart)
    void checkCart(){
        bottomDialogFragment();
    }
    private void bottomDialogFragment() {
        DialogFragment myDialog = new DialogFragment();
        myDialog.show(getFragmentManager().beginTransaction().addToBackStack("UpdateFragment"), "UpdateFragment");
    }

    @OnClick(R.id.btnGlobalConfirm)
    void globalCheckout(){
        if (mName.getText().toString().trim().isEmpty() ||
                mAddress.getText().toString().trim().isEmpty() ||
                mPhone.getText().toString().trim().isEmpty() ||
                mProvince.getText().toString().isEmpty() ||
                mCountry.getText().toString().isEmpty() ||
                mCity.getText().toString().isEmpty() ||
                mPostalCode.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.fill_blank, Toast.LENGTH_SHORT).show();
        } else {
            showDialogLoading("Loading...");
            ApiNetwork.getApiInterface().checkOutGlobal(
                    SessionManager.getInstance().getPrice(),
                    "GLOBAL CHECKOUT",
                    "99999",
                    "GLOBAL CHECKOUT",
                    "GLOBAL",
                    mCountry.getText().toString(),
                    mProvince.getText().toString(),
                    mCity.getText().toString(),
                    mAddress.getText().toString(),
                    mPostalCode.getText().toString(),
                    mPhone.getText().toString()
            ).enqueue(new Callback<CheckoutGlobalResponse>() {
                @Override
                public void onResponse(@NotNull Call<CheckoutGlobalResponse> call,@NotNull  Response<CheckoutGlobalResponse> response) {
                    MainUtils.logSuccessMessage("Success get global checkout");
                    dismissDialogLoading();
                    if (response.isSuccessful()){
                        Toast.makeText(CheckOutGlobalActivity.this, R.string.checkout_success, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckOutGlobalActivity.this, TransferActivity.class);
                        Product product = response.body().getProduct();
                        intent.putExtra(DETAIL_CHECKOUT,product);
                        intent.putExtra(CHECKOUT_KEY, GLOBAL);
                        AppDatabase.deleteAllCart();
                        startActivity(intent);
                        finishAffinity();
                    } else {
                        Toast.makeText(CheckOutGlobalActivity.this, R.string.checkout_error, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<CheckoutGlobalResponse> call,@NotNull  Throwable t) {
                    MainUtils.logErrorMessage("Error get global checkout ", t);
                    Toast.makeText(CheckOutGlobalActivity.this, R.string.checkout_error, Toast.LENGTH_SHORT).show();
                    dismissDialogLoading();
                }
            });
        }
    }

}
