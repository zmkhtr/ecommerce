package com.aflowz.ecommerce.UI.LoginRegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.MainActivity;
import com.aflowz.ecommerce.UI.SplashActivity;
import com.aflowz.ecommerce.Utils.SessionManager;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends BaseActivity implements LoginRegisterContract.View{
    @BindView(R.id.edtLoginUsername)
    EditText mUsername;

    @BindView(R.id.edtLoginPassword)
    EditText mPassword;

    @BindView(R.id.loaderLoginProgress)
    AVLoadingIndicatorView mLoading;

    private LoginRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        hideAppbar();
        presenter = new LoginRegisterPresenter(this, new LoginRegisterRepository());
    }

    @OnClick(R.id.btnLoginToRegister)
    void toRegister(){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @OnClick(R.id.btnLoginProceed)
    void onLoginClick(){
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, R.string.login_validate, Toast.LENGTH_SHORT).show();
        } else {
            presenter.doLogin(username, password);
        }
    }


    @Override
    public void openNewActivity() {
        toSplashScreen();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message,Throwable e) {
        if (!isNetworkConnected(this)){
            Toast.makeText(LoginActivity.this, "Login Fail, Please Check your internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mLoading.setVisibility(View.INVISIBLE);
    }
}
