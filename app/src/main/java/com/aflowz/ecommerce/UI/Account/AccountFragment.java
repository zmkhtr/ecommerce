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
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

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
    ProfileUserData profileUserData;
    private Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
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
    public void onDestroyView() {
        super.onDestroyView();
        setData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
            Timber.d("tt invisible");
            setData();
        } else {
            //do when show
            Timber.d("tt visible");
            setData();
        }
    }

    @Override
    public void onResume() {
        setUserVisibleHint(true);
        super.onResume();
    }

    @Override
    public void onStart() {
        setUserVisibleHint(true);
        super.onStart();
    }

    private void setData(){
        Timber.d("setdata");
        AppRepository appRepository = new AppRepository();
        appRepository.getProfile();
        profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
        ApiNetwork.getApiInterface().getProfileDetail()
                .enqueue(new Callback<ProfileResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                AppDatabase.addProfile(response.body().getProfileUserData());
                                Timber.d("profile %s", response.body().getProfileUserData().getName());
                                profileUserData = response.body().getProfileUserData();
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
                            MainUtils.logSuccessMessage("Success Get Profile");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get profile ", t);
                    }
                });

    }

}
