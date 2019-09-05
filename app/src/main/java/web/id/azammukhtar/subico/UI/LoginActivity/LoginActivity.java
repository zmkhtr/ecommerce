package web.id.azammukhtar.subico.UI.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.id.azammukhtar.subico.Model.UserLogin.DataUser;
import web.id.azammukhtar.subico.Model.UserLogin.User;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.MainActivity.MainActivity;
import web.id.azammukhtar.subico.UI.Register.RegisterActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.edtLoginUsername)
    EditText edtUsername;

    @BindView(R.id.edtLoginPassword)
    EditText edtPassword;

    @BindView(R.id.loaderLoginProgress)
    AVLoadingIndicatorView avLoadingIndicatorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).hide();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLoginProceed)
    void onLoginClick() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Username or password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            doLogin(username, password);
        }
    }

    @OnClick(R.id.btnLoginToRegister)
    void toRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    private void doLogin(String username, String password) {
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        Single<User> call = ApiNetwork.getApiInterface().doLogin(username, password);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        SessionManager.getInstance().setLogin(true, user.getUser().getToken());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        Log.d(TAG, "onSuccess: Login Berhasil " + user.getUser().getToken());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onFailure: Login ", e);
                    }
                });
//        Call<User> call = ApiNetwork.getApiInterface().doLogin(username, password);
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
//                Log.d(TAG, "onResponse: success");
//                Log.d(TAG, "onResponse: " + response.code());
//                switch (response.code()){
//                    case 200:
//                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
//                        DataUser user = response.body().getUser();
//                        if (user.getToken()!= null){
//                            SessionManager.getInstance().setLogin(true, user.getToken());
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                            finish();
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
//                        }
//                        break;
//                    case 400:
//                        Toast.makeText(LoginActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
//                }
//                avLoadingIndicatorView.setVisibility(View.INVISIBLE);
//            }
//            @Override
//            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
//                avLoadingIndicatorView.setVisibility(View.INVISIBLE);
//                Log.e(TAG, "onFailure: ", t);
//            }
//        });
    }
}
