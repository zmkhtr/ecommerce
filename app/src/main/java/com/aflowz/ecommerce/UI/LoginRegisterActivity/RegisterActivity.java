package com.aflowz.ecommerce.UI.LoginRegisterActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.MainActivity;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements LoginRegisterContract.View {

    @BindView(R.id.edtRegisterEmail)
    EditText mEmail;
    @BindView(R.id.edtRegisterFullName)
    EditText mFullName;
    @BindView(R.id.edtRegisterPassword)
    EditText mPassword;
    @BindView(R.id.edtRegisterRePassword)
    EditText mRePassword;
    @BindView(R.id.edtRegisterUserName)
    EditText mUsername;
    @BindView(R.id.edtRegisterPhone)
    EditText mPhone;
    @BindView(R.id.edtRegisterSocialMedia)
    EditText mSocialMedia;
    @BindView(R.id.loaderRegisterProgress)
    AVLoadingIndicatorView mLoading;

    private LoginRegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        hideAppbar();
        presenter = new LoginRegisterPresenter(this, new LoginRegisterRepository());
    }

    @OnClick(R.id.btnRegisterToLogin)
    void backToLogin() {
        onBackPressed();
    }

    @OnClick(R.id.btnRegisterProceed)
    void onRegisterClick() {
        String name = mUsername.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String fullName = mFullName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String rePassword = mRePassword.getText().toString().trim();
        String phone = mPhone.getText().toString().trim();
        String socialMedia = mSocialMedia.getText().toString().trim();
        if (name.isEmpty() || email.isEmpty() || fullName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Please fill all the form, first", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Please enter the valid email address");
        } else if (password.length() <= 5) {
            mPassword.setError("Password min 6 character");
        } else if (!rePassword.equals(password)) {
            mRePassword.setError("Password not same");
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            presenter.doRegister(fullName, name, email, password, rePassword, phone, socialMedia);
        }
    }

    @Override
    public void openNewActivity() {
        Toast.makeText(this, R.string.register_success_message, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message, Throwable e) {
        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "Register Fail, Please Check your internet Connection", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
