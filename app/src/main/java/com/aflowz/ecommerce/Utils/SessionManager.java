package com.aflowz.ecommerce.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.aflowz.ecommerce.Base.BaseApplication;

import timber.log.Timber;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private static SessionManager singleTonInstance = null;
    private static final int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "SessionManager";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String USER_TOKEN = "userToken";
    private static final String USER_NAME = "userName";
    private static final String TOTAL_PRICE = "totalPrice";
    private static final String USER_ROLE = "USER_ROLE";

    public static SessionManager getInstance() {
        if (singleTonInstance == null) {
            singleTonInstance = new SessionManager(BaseApplication.getInstance().getApplicationContext());
        }
        return singleTonInstance;
    }


    private SessionManager(Context context) {
        super();
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.apply();
    }

    public void setPrice(String price){
        editor.putString(TOTAL_PRICE, price);
        editor.commit();
    }
    public void setRole(String role){
        editor.putString(USER_ROLE, role);
        editor.commit();
    }
    public String getRole() {
        return pref.getString(USER_ROLE, "ADMIN");
    }

    public void setLogin(boolean isLoggedIn, String userToken, String userName) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(USER_TOKEN, userToken);
        editor.putString(USER_NAME, userName);
        editor.commit();

        Timber.d("LoginTokenData login session modified!");
    }

    public String getUserName() {
        return pref.getString(USER_NAME, null);
    }

    public String getPrice() {
        return pref.getString(TOTAL_PRICE, null);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUserToken(){
        return pref.getString(USER_TOKEN, null);
    }

    public void clearData() {
        editor.clear().commit();
    }
}
