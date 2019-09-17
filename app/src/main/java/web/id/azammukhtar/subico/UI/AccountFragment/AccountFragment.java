package web.id.azammukhtar.subico.UI.AccountFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import web.id.azammukhtar.subico.Model.Profile.Profile;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.LoginActivity.LoginActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;


public class AccountFragment extends Fragment {
    private static final String TAG = "AccountFragment";
    Unbinder unbinder;
    @BindView(R.id.textProfileEmail)
    TextView textEmail;

    @BindView(R.id.textProfileAddress)
    TextView textAddres;

    @BindView(R.id.textProfileName)
    TextView textName;

    @BindView(R.id.textProfilePhone)
    TextView textPhone;

    @BindView(R.id.textProfileUsername)
    TextView textUsername;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("Frag : ", "account");
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        Button buttonLogout;
//        SessionManager sessionManager = new SessionManager(getContext());
        buttonLogout = view.findViewById(R.id.logout);
        buttonLogout.setOnClickListener(v -> {
            SessionManager.getInstance().clearData();
            Toast.makeText(getContext(), "Logout success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        });
        getDetail();
    }

    private void getDetail(){
        Log.d(TAG, "onSuccess: kk 1 ");
        Single<Profile> call = ApiNetwork.getApiInterface().getProfileDetail("Bearer " + SessionManager.getInstance().getUserToken());
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Profile>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Profile profile) {
                        Log.d(TAG, "onSuccess: kk ");
                        textAddres.setText(profile.getSuccess().getAddress());
                        textEmail.setText(profile.getSuccess().getEmail());
                        textPhone.setText(profile.getSuccess().getPhone());
                        textUsername.setText(profile.getSuccess().getUsername());
                        textName.setText(profile.getSuccess().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: kk ", e);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.search);
        if(item!=null)
            item.setVisible(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
