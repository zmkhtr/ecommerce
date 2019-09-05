package web.id.azammukhtar.subico.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.id.azammukhtar.subico.Adapter.SliderAdapter;
import web.id.azammukhtar.subico.Model.Cart.Cart;
import web.id.azammukhtar.subico.Model.Cart.Cartproduct;
import web.id.azammukhtar.subico.Model.Favorite.Favorite;
import web.id.azammukhtar.subico.Model.ProductDetail.ProductDetail;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static web.id.azammukhtar.subico.UI.FavoriteFragment.FavoriteFragment.FAVORITE_STATUS;
import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    private int value = 1;

    private TextView textNamaItem, textHargaItem;
    private List<String> colorList = new ArrayList<>();
    private List<String> sizeList = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ProductDetail mProductDetail;

    @BindView(R.id.textDetailDesc)
    TextView textDescItem;

    @BindView(R.id.imageDetailSlider)
    SliderView sliderView;

    @BindView(R.id.dropdownDetailUkuran)
    AppCompatAutoCompleteTextView mUkuran;

    @BindView(R.id.dropdownDetailWarna)
    AppCompatAutoCompleteTextView mWarna;

    @BindView(R.id.textDetailValue)
    TextView textValue;

    @BindView(R.id.buttonDetailFavorite)
    ImageView logoFavorite;

    private Context context;
    private SliderAdapter sliderAdapter;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        intent = getIntent();

        boolean status = intent.getBooleanExtra(FAVORITE_STATUS, false);
        if (status){
            logoFavorite.setImageResource(R.drawable.ic_favorite_fill);
        }
        context = getApplicationContext();
        sliderAdapter = new SliderAdapter(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.detailProduk);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        sliderView.setSliderAdapter(sliderAdapter);
        findId();
        getData();
    }


    private void findId() {
        textNamaItem = findViewById(R.id.textDetailNama);
        textHargaItem = findViewById(R.id.textDetailHarga);
    }

    @OnClick(R.id.buttonDetailAdd)
    void buttonAdd() {
        value = value + 1;
        displayValue(value);
    }

    @OnClick(R.id.buttonDetailMin)
    void buttonMin() {
        value = value - 1;
        if (value == 1) {
            displayValue(value);
        }
        displayValue(value);
    }

    private void displayValue(int x) {
        textValue.setText(String.valueOf(x));
    }

    private void setData(ProductDetail productDetail) {
        mProductDetail = productDetail;
        //set text
        textNamaItem.setText(productDetail.getProduct().getName());
        String price = "Rp " + productDetail.getProduct().getPrice();
        textHargaItem.setText(price);
        textDescItem.setText(productDetail.getProduct().getDescription());

        //get List
        colorList.addAll(productDetail.getProduct().getColor());
        sizeList.addAll(productDetail.getProduct().getSize());

        ArrayAdapter<String> adapterWarna =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        colorList);

        mWarna.setText(colorList.get(0));
        mWarna.setAdapter(adapterWarna);

        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        sizeList);

        mUkuran.setText(sizeList.get(0));
        mUkuran.setAdapter(adapterSize);

        //set slide
        sliderAdapter.setImageUrl(productDetail.getProduct().getImages());
        sliderAdapter.notifyDataSetChanged();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        int id = intent.getIntExtra(ID_PRODUCT, 0);
        Log.d(TAG, "onCreate: id " + id);
        Single<ProductDetail> call = ApiNetwork.getApiInterface().getProductsDetail("Bearer " + SessionManager.getInstance().getUserToken(), id);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ProductDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(ProductDetail productDetail) {
                        setData(productDetail);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: detail ", e);
                    }
                });
    }

    @OnClick(R.id.buttonDetailAddToCart)
    void addToCart() {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Menambah ke keranjang...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();
        Log.d(TAG, "addToCart: " + mUkuran.getText().toString() + " " + mWarna.getText().toString());

        Call<Cartproduct> call = ApiNetwork.getApiInterface().addCart(
                "Bearer " + SessionManager.getInstance().getUserToken(),
                mProductDetail.getProduct().getId(),
                value,
                mUkuran.getText().toString(),
                mWarna.getText().toString());

        call.enqueue(new Callback<Cartproduct>() {
            @Override
            public void onResponse(Call<Cartproduct> call, Response<Cartproduct> response) {
                Log.d(TAG, "onSuccess: cart " + response);
                Toast.makeText(DetailActivity.this, "Berhasil menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Cartproduct> call, Throwable t) {
                Log.d(TAG, "onError: cart " + t);
                Toast.makeText(DetailActivity.this, "Terdapat kesalahan pada server : " + t, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.buttonDetailFavorite)
    void addToFavorite() {
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Menambah ke favorite...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.show();

        Call<Favorite> call = ApiNetwork.getApiInterface().addFavorite(
                "Bearer " + SessionManager.getInstance().getUserToken(),
                mProductDetail.getProduct().getId(),
                value);
//
        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                Log.d(TAG, "onSuccess: " + response.body().getWishlists().getData());
                Toast.makeText(DetailActivity.this, "Berhasil menambahkan ke favorite", Toast.LENGTH_SHORT).show();
                logoFavorite.setImageResource(R.drawable.ic_favorite_fill);
//                AppDatabase.addWhistlist(response.body().getWishlists().getData().get());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                Log.d(TAG, "onError: " + t);
                Toast.makeText(DetailActivity.this, "Terdapat kesalahan pada server : " + t, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}
