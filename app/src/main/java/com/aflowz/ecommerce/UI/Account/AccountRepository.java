package com.aflowz.ecommerce.UI.Account;

import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository implements AccountContract.GetAccountRepository{
    @Override
    public void proceedEdit(OnFinishedListener onFinishedListener, String name, String phone, String address) {
        ApiNetwork.getApiInterface().updateProfile(name, phone, address)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            AppRepository appRepository = new AppRepository();
                            appRepository.getProfile();
                            onFinishedListener.onSuccess("Success update Profile");
                        }
                        MainUtils.logSuccessMessage("Success update Profile");
                    }

                    @Override
                    public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error update Profile ", t);
                        onFinishedListener.onError("Error update profile", t);
                    }
                });
    }
}
