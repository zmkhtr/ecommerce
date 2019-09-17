package web.id.azammukhtar.subico;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import web.id.azammukhtar.subico.Adapter.SliderAdapter;
import web.id.azammukhtar.subico.Model.Profile.Profile;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.UI.AccountFragment.AccountFragment;
import web.id.azammukhtar.subico.UI.CartFragment.CartFragment;
import web.id.azammukhtar.subico.UI.FavoriteFragment.FavoriteFragment;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeFragment;
import web.id.azammukhtar.subico.UI.LoginActivity.LoginActivity;
import web.id.azammukhtar.subico.UI.MainActivity.MainActivity;
import web.id.azammukhtar.subico.UI.TabsFragment.TabsFragment;
import web.id.azammukhtar.subico.UI.TransactionFragment.TransactionFragment;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static web.id.azammukhtar.subico.Utils.Constant.IMG_URL;
import static web.id.azammukhtar.subico.Utils.Constant.PROFILE_IMG_URL;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainMenuActivity";

    public static final String SCENARIO = "SCENARIO";
    public static final String CART_SCENARIO = "CART_SCENARIO";


    final Fragment homeFragment = new HomeFragment();
    final Fragment favoriteFragment = new FavoriteFragment();
    final Fragment transactionFragment = new TransactionFragment();
    final Fragment accountFragment = new AccountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    Context context;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    TextView email, name;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setElevation(0);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        EditText editText = toolbar.findViewById(R.id.edtSearchMainMenu);
        editText.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
//        editText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                Log.d(TAG, "onKey: pressed ");
//                boolean handled = false;
//                if (i == EditorInfo.IME_ACTION_SEARCH) {
//                    Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
//                    intent.putExtra("QUERY", editText.getText().toString());
//                    startActivity(intent);
//                    startActivity(intent);
//                    handled = true;
//                }
//                return handled;
//            }
//        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                Log.d(TAG, "onEditorAction: pressed ");
                    Intent intent = new Intent(MainMenuActivity.this, SearchActivity.class);
                    intent.putExtra("QUERY", editText.getText().toString());
                    intent.putExtra("SUBCATEGORY", "");
                    startActivity(intent);
                return true;
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        email = navigationView.getHeaderView(0).findViewById(R.id.navHeaderEmail);
        name =  navigationView.getHeaderView(0).findViewById(R.id.navHeaderName);
        image =  navigationView.getHeaderView(0).findViewById(R.id.navHeaderImage);

        if (!ApiNetwork.isNetworkConnected(this)){
            showSnackBar();
        } else {
            fm.beginTransaction().add(R.id.main_container, accountFragment, "4").hide(accountFragment).commit();
            fm.beginTransaction().add(R.id.main_container, transactionFragment, "3").hide(transactionFragment).commit();
            fm.beginTransaction().add(R.id.main_container, favoriteFragment, "2").hide(favoriteFragment).commit();
            fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
        }
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            active = getSupportFragmentManager().getFragment(savedInstanceState, "tag");
        }

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
                        email.setText(profile.getSuccess().getEmail());
                        name.setText(profile.getSuccess().getPhone());
                        Glide.with(context)
                                .load(PROFILE_IMG_URL + profile.getSuccess().getPhoto())
                                .apply(RequestOptions.circleCropTransform())
                                .placeholder(R.drawable.ic_broken_image)
                                .into(image);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: kk ", e);
                    }
                });
    }

    private void showSnackBar(){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.mainActivity), "NO INTERNET CONNECTION DETECTED", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", view -> {
                    if (ApiNetwork.isNetworkConnected(this)){
                        fm.beginTransaction().add(R.id.main_container, accountFragment, "4").hide(accountFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, transactionFragment, "3").hide(transactionFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, favoriteFragment, "2").hide(favoriteFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
                    } else {
                        showSnackBar();
                    }
                });
        snackbar.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_cart) {
            Intent intent = new Intent(this, BlankActivity.class);
            intent.putExtra(SCENARIO, CART_SCENARIO);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fm.beginTransaction().hide(active).show(homeFragment).commit();
            active = homeFragment;
        } else if (id == R.id.nav_favorite) {
            fm.beginTransaction().hide(active).show(favoriteFragment).commit();
            active = favoriteFragment;
        } else if (id == R.id.nav_transaction) {
            fm.beginTransaction().hide(active).show(transactionFragment).commit();
            active = transactionFragment;
        } else if (id == R.id.nav_account) {
            fm.beginTransaction().hide(active).show(accountFragment).commit();
            active = accountFragment;
        } else if (id == R.id.nav_logout) {
            SessionManager.getInstance().clearData();
            Toast.makeText(this, "Logout success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        fm.beginTransaction().show(active).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fm.beginTransaction().hide(active).commit();
    }
}
