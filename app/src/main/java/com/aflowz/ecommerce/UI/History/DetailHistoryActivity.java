package com.aflowz.ecommerce.UI.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.DetailOrderResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.EditOrderResponse;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.canner.stepsview.StepsView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.aflowz.ecommerce.Utils.Constant.ADMIN_KEY;
import static com.aflowz.ecommerce.Utils.Constant.ORDER_ID;

public class DetailHistoryActivity extends BaseActivity {

    @BindView(R.id.stepsView)
    StepsView mStepsView;

    DetailOrderResponse detailOrderResponse;
    String detail;

    @BindView(R.id.textHistoryDate)
    TextView mDate;
    @BindView(R.id.textHistoryNote)
    TextView mNote;
    @BindView(R.id.textHistoryResi)
    TextView mResi;
    @BindView(R.id.textHistoryShippingMethod)
    TextView mShipping;
    @BindView(R.id.textHistoryTotalPrice)
    TextView mPrice;
    @BindView(R.id.textHistoryOrderCode)
    TextView mOrderCode;

    @BindView(R.id.dropdownAdminHistoryStatus)
    AutoCompleteTextView mStatus;

    @BindView(R.id.edtAdminHistoryResi)
    EditText mUpdateResi;

    @BindView(R.id.layoutAdminHistory)
    LinearLayout mLayoutAdmin;

    int orderId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        orderId = intent.getIntExtra(ORDER_ID,0);
        getHistoryDetail(orderId);
        boolean admin = intent.getBooleanExtra(ADMIN_KEY, false);

        if (admin){
            mLayoutAdmin.setVisibility(View.VISIBLE);
        }

        setAppBarTitle("Order History Detail");
        setUpButton();
    }

    private void setStepView(){
        String[] steps = {"UNPAID", "PROCESS", "CONFIRM", "DELIVERY", "COMPLETE", "CANCEL"};
        int position = 0;
        detail = detailOrderResponse.getOrder().getStatus();
        switch (detail) {
            case "UNPAID":
                position = 0;
                break;
            case "PROCESS":
                position = 1;
                break;
            case "CONFIRM":
                position = 2;
                break;
            case "DELIVERY":
                position = 3;
                break;
            case "COMPLETE":
                position = 4;
                break;
            case "CANCELED":
                position = 5;
                break;
        }
        mStepsView.setLabels(steps)
                .setBarColorIndicator(getApplicationContext().getResources().getColor(R.color.grey))
                .setProgressColorIndicator(getApplicationContext().getResources().getColor(R.color.colorAccent))
                .setLabelColorIndicator(getApplicationContext().getResources().getColor(R.color.colorPrimary))
                .setCompletedPosition(position)
                .drawView();
    }

    private void setData(){
        String date = "Order Date : " + detailOrderResponse.getOrder().getCreatedAt();
        mDate.setText(date);

        String note;
        if (detailOrderResponse.getOrder().getNoResi() == null) {
            note = "";
        } else {
            note = "Note : \n"+ detailOrderResponse.getOrder().getNote();
        }
        mNote.setText(note);

        String price = "Total Price \n" + MainUtils.formatRupiah(String.valueOf(detailOrderResponse.getOrder().getTotalPrice()));
        mPrice.setText(price);

        String noResi;
        if (detailOrderResponse.getOrder().getNoResi() == null) {
            noResi = "No Resi : Not Added Yet";
        } else {
            noResi = "No Resi : " + detailOrderResponse.getOrder().getNoResi();
        }
        mResi.setText(noResi);

        mShipping.setText(detailOrderResponse.getOrder().getShippingMethod());
        String order = "Order#" + detailOrderResponse.getOrder().getCode();
        mOrderCode.setText(order);

        List<String> statusList = new ArrayList<>();

        statusList.add("UNPAID");
        statusList.add("PROCESS");
        statusList.add("CONFIRM");
        statusList.add("DELIVERY");
        statusList.add("COMPLETE");
        statusList.add("CANCELED");
        ArrayAdapter<String> adapterStatus =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        statusList);

        mStatus.setText(detailOrderResponse.getOrder().getStatus());
        mStatus.setAdapter(adapterStatus);
        mUpdateResi.setText(detailOrderResponse.getOrder().getNoResi());
    }

    @OnClick(R.id.btnAdminHistoryUpdate)
    void update(){
        showDialogLoading("Loading...");
        ApiNetwork.getApiInterface().editOrder(orderId, mStatus.getText().toString(), mUpdateResi.getText().toString().trim())
                .enqueue(new Callback<EditOrderResponse>() {
                    @Override
                    public void onResponse(Call<EditOrderResponse> call, Response<EditOrderResponse> response) {
                        if(response.isSuccessful()){
                            MainUtils.logSuccessMessage("Successful update detail order");
                            getHistoryDetail(orderId);
                            dismissDialogLoading();
                            Toast.makeText(DetailHistoryActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                        }
                        MainUtils.logSuccessMessage("Success get update order");
                        dismissDialogLoading();
                    }

                    @Override
                    public void onFailure(Call<EditOrderResponse> call, Throwable t) {
                        MainUtils.logErrorMessage("Error update detail order", t);
                        Toast.makeText(DetailHistoryActivity.this, "Update Fail", Toast.LENGTH_SHORT).show();
                        dismissDialogLoading();
                    }
                });
    }
    private void getHistoryDetail(int id){
        ApiNetwork.getApiInterface().getDetailOrder(id)
                .enqueue(new Callback<DetailOrderResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<DetailOrderResponse> call,@NotNull Response<DetailOrderResponse> response) {
                        if(response.isSuccessful()){
                            detailOrderResponse = response.body();
                            detail = detailOrderResponse.getOrder().getStatus();
                            MainUtils.logSuccessMessage("Successful get detail order");
                            setStepView();
                            setData();
                        }
                        MainUtils.logSuccessMessage("Success get detail order");
                    }

                    @Override
                    public void onFailure(@NotNull Call<DetailOrderResponse> call,@NotNull  Throwable t) {
                        MainUtils.logErrorMessage("Error get detail order", t);
                    }
                });
    }
    //    1.	UNPAID
    //    2.	PROCESS
    //    3.	CONFIRM
    //    4.	DELIVERY
    //    5.	COMPLETE
    //    6.	CANCELED
}
