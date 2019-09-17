package web.id.azammukhtar.subico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.id.azammukhtar.subico.Adapter.SearchFragmentAdapter;
import web.id.azammukhtar.subico.Model.Product.Datum;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.UI.DetailActivity;
import web.id.azammukhtar.subico.UI.MainActivity.MainActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static web.id.azammukhtar.subico.MainMenuActivity.CART_SCENARIO;
import static web.id.azammukhtar.subico.MainMenuActivity.SCENARIO;
import static web.id.azammukhtar.subico.UI.DetailActivity.RENT_CODE;
import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private String query, category, subcategory;

//    @BindView(R.id.editTextSearchQuery)
//    EditText editTextSearch;

    @BindView(R.id.dropdownSearchKategori)
    AutoCompleteTextView autoCompleteTextViewKategori;

    @BindView(R.id.dropdownSearchSort)
    AutoCompleteTextView autoCompleteTextViewSort;

    @BindView(R.id.dropdownSearchSubKategori)
    AutoCompleteTextView autoCompleteTextViewSubKategori;

    @BindView(R.id.recyclerSearch)
    RecyclerView recyclerView;

    String code;

    EditText editText;

    SearchFragmentAdapter searchFragmentAdapter;
    private List<Datum> datumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        query = intent.getStringExtra("QUERY");
        category = intent.getStringExtra("SUBCATEGORY");
        code = intent.getStringExtra(RENT_CODE);

        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setElevation(0);
        setSupportActionBar(toolbar);

        editText = toolbar.findViewById(R.id.edtSearchMainMenu);


        searchFragmentAdapter = new SearchFragmentAdapter(this);
        Log.d(TAG, "handleIntent: " + query);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Hasil pencarian");
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(searchFragmentAdapter);
        searchFragmentAdapter.setOnItemClickListener(position -> {
            Log.d(TAG, "onViewCreated: click " + position);
            Datum productModel = searchFragmentAdapter.getProductModels().get(position);
            Intent i = new Intent(SearchActivity.this, DetailActivity.class);
            Log.d(TAG, "onViewCreated: " + productModel.getName() + productModel.getId());
            i.putExtra(ID_PRODUCT, productModel.getId());
            if (code != null) {
                i.putExtra(RENT_CODE, code);
            }
            startActivity(i);
        });
//        editTextSearch.setText(query);
        searchProduct(query, subcategory, null);
        setData();
        search();

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

    private void search(){
        editText.setText(query);
        editText.setImeActionLabel("Search", KeyEvent.KEYCODE_ENTER);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                searchProduct(editText.getText().toString(),
                        autoCompleteTextViewKategori.getText().toString(),
                        autoCompleteTextViewSort.getText().toString());
                return true;
            }
        });
    }

    private void setData(){
        List<String> kategoriList = new ArrayList<>();
        kategoriList.add("Woman");
        kategoriList.add("Men");
        kategoriList.add("ATK");
        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        kategoriList);

//        autoCompleteTextViewKategori.setText(kategoriList.get(0));
        autoCompleteTextViewKategori.setAdapter(adapterSize);

        List<String> subKatgoeriList = new ArrayList<>();
        subKatgoeriList.add("Baju");
        subKatgoeriList.add("Sepatu");
        subKatgoeriList.add("Celana");
        subKatgoeriList.add("Rok");
        subKatgoeriList.add("Tas");
        ArrayAdapter<String> adapterSize1 =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        subKatgoeriList);

//        autoCompleteTextViewSubKategori.setText(subKatgoeriList.get(0));
        autoCompleteTextViewSubKategori.setAdapter(adapterSize1);


        List<String> price = new ArrayList<>();
        price.add("price-asc");
        price.add("price-desc");
        ArrayAdapter<String> adapterSize2 =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        price);

//        autoCompleteTextViewSort.setText(price.get(0));
        autoCompleteTextViewSort.setAdapter(adapterSize2);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                searchProduct(editTextSearch.getText().toString(),
//                        autoCompleteTextViewKategori.getText().toString(),
//                        autoCompleteTextViewSort.getText().toString());
//            }
//        });
    }

    private void searchProduct(String keyword, String subcategory, String sort ){
        Call<Product> call = ApiNetwork.getApiInterface()
                .searchFilterProducts(
                        "Bearer " + SessionManager.getInstance().getUserToken(),1,keyword,subcategory,sort);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()){
                    datumList.clear();
                    datumList.addAll(response.body().getProduct().getData());
                    searchFragmentAdapter.setProductModels(datumList);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


}
