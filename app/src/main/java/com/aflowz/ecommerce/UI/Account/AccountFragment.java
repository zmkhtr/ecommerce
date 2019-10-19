package com.aflowz.ecommerce.UI.Account;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aflowz.ecommerce.Base.BaseFragment;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment {

    @BindView(R.id.textProfileEmail)
    TextView mEmail;
    @BindView(R.id.textProfileName)
    TextView mName;
    @BindView(R.id.textProfilePhone)
    TextView mPhone;
    @BindView(R.id.textProfileAddress)
    TextView mAddress;
    @BindView(R.id.textProfileUsername)
    TextView mUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setData();
    }

    @OnClick(R.id.buttonProfileEditAddress)
    void editProfile(){
        setUserVisibleHint(false);
        startActivity(new Intent(getContext(), EditAccountActivity.class));
    }

    @OnClick(R.id.buttonProfileEditProfile)
    void editAddress(){
        setUserVisibleHint(false);
        startActivity(new Intent(getContext(), EditAccountActivity.class));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            setData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserVisibleHint(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        setUserVisibleHint(true);
    }

    private void setData(){
        ProfileUserData profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
        mEmail.setText(profileUserData.getEmail());
        mName.setText(profileUserData.getName());
        mPhone.setText(profileUserData.getPhone());
        mUsername.setText(profileUserData.getUsername());
        if (profileUserData.getAddress() == null){
            mAddress.setText(R.string.not_add_address);
        } else {
            mAddress.setText(profileUserData.getAddress());
        }
    }

}
