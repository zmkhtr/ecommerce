package com.aflowz.ecommerce.Base;

import android.app.Application;

import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Utils.SessionManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.DB_NAME;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        Timber.plant(new Timber.DebugTree());
        initRealm();
    }



    private void initRealm() {
        Realm.init(this);
        RealmConfiguration defaultRealmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build();
        Realm.setDefaultConfiguration(defaultRealmConfiguration);
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
}
