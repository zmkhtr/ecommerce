package web.id.azammukhtar.subico.Utils;

import android.app.Application;


import io.reactivex.disposables.Disposable;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static web.id.azammukhtar.subico.Network.ApiNetwork.isNetworkConnected;
import static web.id.azammukhtar.subico.Utils.Constant.DB_NAME;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        initRealm();
        isNetworkConnected(this);
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

    public static MyApplication getInstance() {
        return mInstance;
    }

}
