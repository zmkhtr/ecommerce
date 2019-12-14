package com.aflowz.ecommerce.UI.LoginRegisterActivity;

import androidx.annotation.NonNull;

import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseLogin.LoginResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRegister.RegisterResponse;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class LoginRegisterRepository implements LoginRegisterContract.GetLoginRepository{

    @Override
    public void proceedLogin(OnFinishedListener onFinishedListener, String username, String password) {
        Timber.d("Login nih");
        ApiNetwork.getApiInterface().doLogin(username, password)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call,@NonNull Response<LoginResponse> response) {
                        Timber.d("Response code %s", response.code());
                        if (response.isSuccessful()){
                            if (response.body().getStatus() == 403){
                                onFinishedListener.onWrongPassOrEmail("Wrong Password or Email");
                            } else  if (response.body().getStatus() == 200) {
                                onFinishedListener.onSuccess("Login Success");
                                SessionManager.getInstance().setLogin(true, response.body().getLoginTokenData().getToken(), username);
                                MainUtils.logSuccessMessage("Login Success " + response.body().getLoginTokenData().getToken());
                            }
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponse> call,@NonNull Throwable t) {
                        onFinishedListener.onError("Login Fail, there is an Error when connecting to Server ", t);
                        MainUtils.logErrorMessage("Login Fail ", t);
                    }
                });
    }

    @Override
    public void proceedRegister(OnFinishedListener onFinishedListener, String name, String username, String email, String password, String rePassword, String phone, String socialMedia) {
        Timber.d("Register nih");
        ApiNetwork.getApiInterface().doRegister(name, username, email, password, rePassword, phone, socialMedia)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<RegisterResponse> call,@NonNull Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                onFinishedListener.onSuccess("Register Success");
                            }
                            MainUtils.logSuccessMessage("Register Success " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        onFinishedListener.onError("Register Fail, there is an Error when connecting to Server ", t);
                        MainUtils.logErrorMessage("Register Fail ", t);
                    }
                });
    }

}
