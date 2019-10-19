package com.aflowz.ecommerce.UI;

import android.content.Intent;
import android.os.Bundle;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.UI.History.HistoryFragment;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.Account.AccountFragment;
import com.aflowz.ecommerce.UI.CartActivity.CartActivity;
import com.aflowz.ecommerce.UI.FavoriteFragment.FavoriteFragment;
import com.aflowz.ecommerce.UI.LoginRegisterActivity.LoginActivity;
import com.aflowz.ecommerce.UI.MainFragment.MainFragment;
import com.aflowz.ecommerce.UI.ProductActivity.ProductActivity;
import com.aflowz.ecommerce.Utils.SessionManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;


import timber.log.Timber;

import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.CATEGORY_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.PRODUCT_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.QUERY_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.SUB_CATEGORY_KEY;
import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;
import static com.aflowz.ecommerce.Utils.Constant.PROFILE_IMG_URL;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textCartItemCount, mEmail, mName;
    ImageView mImage;
    int mCartItemCount;


    Fragment mainFragment = new MainFragment();
    Fragment favoriteFragment = new FavoriteFragment();
    Fragment historyFragment = new HistoryFragment();
    Fragment accountFragment = new AccountFragment();
    FragmentManager fm = getSupportFragmentManager();
    Fragment active = mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mEmail = navigationView.getHeaderView(0).findViewById(R.id.navHeaderEmail);
        mName = navigationView.getHeaderView(0).findViewById(R.id.navHeaderName);
        mImage = navigationView.getHeaderView(0).findViewById(R.id.navHeaderImage);

        EditText editText = toolbar.findViewById(R.id.edtSearchMain);
        editText.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        editText.setOnEditorActionListener((textView, i, keyEvent) -> {
            Timber.d("onEditorAction: pressed ");
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra(QUERY_KEY, editText.getText().toString());
            intent.putExtra(PRODUCT_KEY, "SALE");
            intent.putExtra(CATEGORY_KEY, "");
            intent.putExtra(SUB_CATEGORY_KEY, "");
            intent.putExtra(QUERY_KEY, "");
            startActivity(intent);
            return true;
        });
        setHeaderData();
        setFragment();
    }


    @Override
    protected void onStart() {
        super.onStart();
        setupBadge();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupBadge();
        //fetchRepository();
        fm.beginTransaction().show(active).commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        fm.beginTransaction().hide(active).commit();
    }

    private void setFragment(){
            fm.beginTransaction().add(R.id.main_layout_container, accountFragment, "4").hide(accountFragment).commit();
            fm.beginTransaction().add(R.id.main_layout_container, historyFragment, "3").hide(historyFragment).commit();
            fm.beginTransaction().add(R.id.main_layout_container, favoriteFragment, "2").hide(favoriteFragment).commit();
            fm.beginTransaction().add(R.id.main_layout_container, mainFragment, "1").commit();
    }

    private void setHeaderData() {
        ProfileUserData profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
//        Timber.d("profile data %s", profileUserData.getName());
        mEmail.setText(profileUserData.getEmail());
        mName.setText(profileUserData.getName());
        Glide.with(this)
                .load(PROFILE_IMG_URL + profileUserData.getPhoto())
                .apply(RequestOptions.circleCropTransform())
                .into(mImage);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem menuItem = menu.findItem(R.id.action_cart);
        // Inflate the menu; this adds items to the action bar if it is present.
        View actionView = menuItem.getActionView();
        textCartItemCount = actionView.findViewById(R.id.cart_badge);

        actionView.setOnClickListener(v -> onOptionsItemSelected(menuItem));
        setupBadge();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            startActivity(new Intent(this, CartActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NotNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fm.beginTransaction().hide(active).show(mainFragment).commit();
            active = mainFragment;
        } else if (id == R.id.nav_favorite) {
            fm.beginTransaction().hide(active).show(favoriteFragment).commit();
            active = favoriteFragment;
        } else if (id == R.id.nav_history) {
            fm.beginTransaction().hide(active).show(historyFragment).commit();
            active = historyFragment;
        } else if (id == R.id.nav_account) {
            fm.beginTransaction().hide(active).show(accountFragment).commit();
            active = accountFragment;
        } else if (id == R.id.nav_logout) {
            SessionManager.getInstance().clearData();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupBadge() {
        mCartItemCount = AppDatabase.getCart().size();

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
