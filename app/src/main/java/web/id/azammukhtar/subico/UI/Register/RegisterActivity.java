package web.id.azammukhtar.subico.UI.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import web.id.azammukhtar.subico.Model.UserLogin.User;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    @BindView(R.id.edtRegisterEmail)
    EditText edtEmail;
    @BindView(R.id.edtRegisterFullName)
    EditText edtFullname;
    @BindView(R.id.edtRegisterPassword)
    EditText edtPassword;
    @BindView(R.id.edtRegisterRePassword)
    EditText edtRePassword;
    @BindView(R.id.loaderRegisterProgress)
    AVLoadingIndicatorView avLoadingIndicatorView;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    @OnClick(R.id.btnRegisterProceed)
    void onClickRegister() {
        String name = edtEmail.getText().toString();
        String email = edtEmail.getText().toString();
        String fullName = edtFullname.getText().toString();
        String password = edtPassword.getText().toString();
        String rePassword = edtRePassword.getText().toString();
        if (name.isEmpty() || email.isEmpty() || fullName.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(this, "Please fill all the form, first", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Please enter the valid email address");
        } else if (password.length() <= 5) {
            edtPassword.setError("Password min 6 character");
        } else if (!rePassword.equals(password)) {
            edtRePassword.setError("Password not same");
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            doRegister(name, email, fullName, password, rePassword);
        }
    }

    @OnClick(R.id.btnRegisterToLogin)
    void toLogin(){
        onBackPressed();
    }

    private void doRegister(String name, String email, String fullname, String password, String repassword) {
        avLoadingIndicatorView.show();
        Single<User> call = ApiNetwork.getApiInterface().doRegister(fullname, name, email, password, repassword);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(User user) {
                        User userGet = new User();
                        userGet.setUser(user.getUser());
                        Log.d(TAG, "onSuccess: register " + user);
                        avLoadingIndicatorView.hide();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: register ", e);
                        avLoadingIndicatorView.hide();
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
