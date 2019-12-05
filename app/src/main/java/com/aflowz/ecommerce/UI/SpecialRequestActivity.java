package com.aflowz.ecommerce.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class SpecialRequestActivity extends BaseActivity {

    @BindView(R.id.edtSpecialMessage)
    EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_request);

        ButterKnife.bind(this);

        setAppBarTitle("Special Request");
        setUpButton();
    }

    @OnClick(R.id.btnSpecialSend)
    void sendMessage(){
        if (mMessage.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill in the message", Toast.LENGTH_SHORT).show();
        } else {
            showDialogLoading("Loading...");
            ApiNetwork.getApiInterface().sendSpecialRequest(mMessage.getText().toString().trim())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(SpecialRequestActivity.this, "Special Request Sent !", Toast.LENGTH_SHORT).show();
                            }
                            dismissDialogLoading();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dismissDialogLoading();
                            Timber.d(t, " Error ");
                        }
                    });
        }
    }
}
