package com.aflowz.ecommerce.Base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.UI.SplashActivity;
import com.aflowz.ecommerce.Utils.SessionManager;
import com.chuckerteam.chucker.api.ChuckerInterceptor;

import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import timber.log.Timber;


public abstract class BaseActivity extends AppCompatActivity {

    private static ProgressDialog progressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);

    }

    public void fetchRepository(){
        AppRepository appRepository;
        appRepository = new AppRepository();
        if (SessionManager.getInstance().isLoggedIn()){
            appRepository.getCategory();
            appRepository.getProfile();
            appRepository.getCart();
            appRepository.getSubCategory();
            appRepository.getFavorite();
            appRepository.getHomeProduct();
            appRepository.getProvince();
            appRepository.getCourier();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return (cm != null ? cm.getActiveNetworkInfo() : null) != null && cm.getActiveNetworkInfo().isConnected();
    }

    public void showDialogLoading(String message){
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
    }

    public void dismissDialogLoading(){
        progressDialog.dismiss();
    }

    public void hideAppbar(){
        if(getSupportActionBar() != null)
        getSupportActionBar().hide();
    }


    public void setUpButton(){
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void setAppBarTitle(String title){
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }
    public void toSplashScreen(){
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Timber.d("Back Pressed");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
