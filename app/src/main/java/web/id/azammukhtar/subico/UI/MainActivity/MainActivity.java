package web.id.azammukhtar.subico.UI.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import web.id.azammukhtar.subico.Model.ProductModel;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.SearchActivity;
import web.id.azammukhtar.subico.UI.TabsFragment.TabsFragment;
import web.id.azammukhtar.subico.UI.AccountFragment.AccountFragment;
import web.id.azammukhtar.subico.UI.CartFragment.CartFragment;
import web.id.azammukhtar.subico.UI.FavoriteFragment.FavoriteFragment;
import web.id.azammukhtar.subico.UI.TransactionFragment.TransactionFragment;

public class MainActivity extends AppCompatActivity {

    public static final String POST_KEY = "POST_KEY";
    public static final String ID_PRODUCT = "ID_PRODUCT";
    final Fragment homeFragment = new TabsFragment();
    final Fragment favoriteFragment = new FavoriteFragment();
    final Fragment cartFragment = new CartFragment();
    final Fragment transactionFragment = new TransactionFragment();
    final Fragment accountFragment = new AccountFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        populateModel();

        ButterKnife.bind(this);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (!ApiNetwork.isNetworkConnected(this)){
           showSnackBar();
        } else {
            fm.beginTransaction().add(R.id.main_container, accountFragment, "5").hide(accountFragment).commit();
            fm.beginTransaction().add(R.id.main_container, transactionFragment, "4").hide(transactionFragment).commit();
            fm.beginTransaction().add(R.id.main_container, cartFragment, "3").hide(cartFragment).commit();
            fm.beginTransaction().add(R.id.main_container, favoriteFragment, "2").hide(favoriteFragment).commit();
            fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
        }
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            active = getSupportFragmentManager().getFragment(savedInstanceState, "tag");
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_action_menu, menu);
//
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_action_menu, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        if (active == homeFragment) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("QUERY", s);
                    startActivity(intent);
                    searchView.clearFocus();
                    searchView.setQuery("", false);
                    searchView.setIconified(true);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
        } else {
            searchView.setVisibility(View.INVISIBLE);
        }


        return true;
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, "tag", active);
    }

    private void showSnackBar(){
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.mainActivity), "NO INTERNET CONNECTION DETECTED", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", view -> {
                    if (ApiNetwork.isNetworkConnected(context)){
                        fm.beginTransaction().add(R.id.main_container, accountFragment, "5").hide(accountFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, transactionFragment, "4").hide(transactionFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, cartFragment, "3").hide(cartFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, favoriteFragment, "2").hide(favoriteFragment).commit();
                        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit();
                    } else {
                        showSnackBar();
                    }
                });
        snackbar.show();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.item_home:
                        fm.beginTransaction().hide(active).show(homeFragment).commit();
                        active = homeFragment;
                        getSupportActionBar().setTitle("Subico");
                        return true;
                    case R.id.item_favorite:
                        fm.beginTransaction().hide(active).show (favoriteFragment).commit();
                        active = favoriteFragment;
                        getSupportActionBar().setTitle("Favorite");
                        return true;
                    case R.id.item_cart:
                        fm.beginTransaction().hide(active).show(cartFragment).commit();
                        active = cartFragment;
                        getSupportActionBar().setTitle("Cart");
                        return true;
                    case R.id.item_transaction:
                        fm.beginTransaction().hide(active).show(transactionFragment).commit();
                        active = transactionFragment;
                        getSupportActionBar().setTitle("Transaction");
                        return true;
                    case R.id.item_account:
                        fm.beginTransaction().hide(active).show(accountFragment).commit();
                        active = accountFragment;
                        getSupportActionBar().setTitle("Account");
                        return true;
                }
                return false;
            };



    public static List<ProductModel> populateModel(){
        List<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/gglxDT0Sv9PutUXNya3vIKU4IpUt0IrFmLpWVYjo.jpeg",
                "Celana Bahan",
                "Rp. 400,000",
                "adem"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/SXBRk4XJ4te9xWXAWbJLtqkpGBlVHuXgsTxd6Udp.jpeg",
                "Tooth Bag",
                "Rp. 600,000",
                "bagus"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/gglxDT0Sv9PutUXNya3vIKU4IpUt0IrFmLpWVYjo.jpeg",
                "Celana Bahan",
                "Rp. 400,000",
                "adem"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/SXBRk4XJ4te9xWXAWbJLtqkpGBlVHuXgsTxd6Udp.jpeg",
                "Tooth Bag",
                "Rp. 600,000",
                "bagus"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/gglxDT0Sv9PutUXNya3vIKU4IpUt0IrFmLpWVYjo.jpeg",
                "Celana Bahan",
                "Rp. 400,000",
                "adem"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/SXBRk4XJ4te9xWXAWbJLtqkpGBlVHuXgsTxd6Udp.jpeg",
                "Tooth Bag",
                "Rp. 600,000",
                "bagus"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/gglxDT0Sv9PutUXNya3vIKU4IpUt0IrFmLpWVYjo.jpeg",
                "Celana Bahan",
                "Rp. 400,000",
                "adem"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/SXBRk4XJ4te9xWXAWbJLtqkpGBlVHuXgsTxd6Udp.jpeg",
                "Tooth Bag",
                "Rp. 600,000",
                "bagus"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/gglxDT0Sv9PutUXNya3vIKU4IpUt0IrFmLpWVYjo.jpeg",
                "Celana Bahan",
                "Rp. 400,000",
                "adem"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/SXBRk4XJ4te9xWXAWbJLtqkpGBlVHuXgsTxd6Udp.jpeg",
                "Tooth Bag",
                "Rp. 600,000",
                "bagus"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        productModels.add(new ProductModel(
                "http://subicommerce.aflowz.com/storage/products/WGjeCaAb4qcy3XiVcbYGo7l1tFKFYNlXIRCHq68P.jpeg",
                "Rok Jeans",
                "Rp. 1.000,000",
                "mantap"));
        return productModels;
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
