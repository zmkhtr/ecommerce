package web.id.azammukhtar.subico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

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

import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private String query;

    @BindView(R.id.editTextSearchQuery)
    EditText editTextSearch;

    @BindView(R.id.dropdownSearchKategori)
    AutoCompleteTextView autoCompleteTextViewKategori;

    @BindView(R.id.dropdownSearchSort)
    AutoCompleteTextView autoCompleteTextViewSort;

    @BindView(R.id.dropdownSearchSubKategori)
    AutoCompleteTextView autoCompleteTextViewSubKategori;

    @BindView(R.id.recyclerSearch)
    RecyclerView recyclerView;

    @BindView(R.id.buttonSearch)
    Button button;

    SearchFragmentAdapter searchFragmentAdapter;
    private List<Datum> datumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        query = intent.getStringExtra("QUERY");
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
            startActivity(i);
        });
        editTextSearch.setText(query);
        searchProduct(query, null, null);
        setData();
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchProduct(editTextSearch.getText().toString(),
                        autoCompleteTextViewKategori.getText().toString(),
                        autoCompleteTextViewSort.getText().toString());
            }
        });
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
