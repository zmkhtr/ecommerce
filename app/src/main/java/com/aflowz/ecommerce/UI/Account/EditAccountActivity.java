package com.aflowz.ecommerce.UI.Account;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditAccountActivity extends BaseActivity implements AccountContract.View {

    @BindView(R.id.edtEditName)
    EditText mName;
    @BindView(R.id.edtEditAddress)
    EditText mAddress;
    @BindView(R.id.edtEditPhone)
    EditText mPhone;

    private AccountContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ButterKnife.bind(this);
        setData();
        setAppBarTitle("Edit Profile");
        setUpButton();
        presenter = new AccountPresenter(this, new AccountRepository());
    }

    private void setData(){
        ProfileUserData profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
        mName.setText(profileUserData.getName());
        mAddress.setText(profileUserData.getAddress());
        mPhone.setText(profileUserData.getEmail());
    }


    @OnClick(R.id.btnEditConfirm)
    void confirmEdit(){
        presenter.doEdit(mName.getText().toString(), mPhone.getText().toString(), mAddress.getText().toString());
    }

    @Override
    public void openNewActivity() {
        onBackPressed();
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message, Throwable e) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        showDialogLoading("Updating profile...");
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }
}
