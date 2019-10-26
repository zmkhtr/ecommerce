package com.aflowz.ecommerce.UI.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductDetail.ProductDetailData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProductSewaDetail.ProductDetailRentData;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.google.android.material.textfield.TextInputLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.PRODUCT_KEY;
import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;

public class DetailActivity extends BaseActivity implements DetailContract.View{

    @BindView(R.id.textDetailNama)
    TextView mNama;
    @BindView(R.id.textDetailHarga)
    TextView mHarga;
    @BindView(R.id.textDetailDesc)
    TextView mDesc;
    @BindView(R.id.textDetailValue)
    TextView mQuantity;
    @BindView(R.id.dropdownDetailHari)
    AutoCompleteTextView mDay;
    @BindView(R.id.dropdownDetailUkuran)
    AutoCompleteTextView mSize;
    @BindView(R.id.dropdownDetailWarna)
    AutoCompleteTextView mColor;
    @BindView(R.id.imageDetailSliderView)
    SliderView mImages;

    @BindView(R.id.buttonDetailAddToCart)
    Button mAddToCart;

    @BindView(R.id.textInputDetailHari)
    TextInputLayout mDayWrapper;

    @BindView(R.id.buttonDetailFavorite)
    ImageView mAddFavorite;

    private int value = 1;
    private List<String> colorList = new ArrayList<>();
    private List<String> sizeList = new ArrayList<>();
    private List<String> priceList = new ArrayList<>();
    private SliderAdapter sliderAdapter;

    private AppRepository appRepository;

    private int id;

    private DetailContract.Presenter presenter;
    String productKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intentKey;
        intentKey = getIntent();
        productKey = intentKey.getStringExtra(PRODUCT_KEY);

        appRepository = new AppRepository();

        sliderAdapter = new SliderAdapter(this);
        Intent intent = getIntent();
        id = intent.getIntExtra(PRODUCT_ID, 0);

        presenter = new DetailPresenter(this, new DetailRepository());

        getDetailData();
        setAppBarTitle("Product Detail");
        setUpButton();
        checkFavorite();
    }

    private void checkFavorite(){
        if (AppDatabase.checkFavorite(id) != null){
            mAddFavorite.setImageResource(R.drawable.ic_like);
        }
    }

    private void getDetailData(){
        if (productKey != null) {
            if (productKey.equals("SALE")) {
                presenter.getDetail(id);
            } else if (productKey.equals("RENT")) {
                presenter.getDetailRent(id);
            }
        }

    }



    @Override
    public void showSuccessMessage(String message) {
        switch (message) {
            case "Success add to favorite":
                mAddFavorite.setImageResource(R.drawable.ic_like);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            case "Success get Detail":
                Timber.d("success detail");
                break;
            case "Success add to cart":
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            case "Success remove favorite":
                mAddFavorite.setImageResource(R.drawable.ic_unlike);
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                break;
            case "Success get rent Detail":
                Timber.d("success rent detail");
                break;
                default:
                    Timber.d(message);
        }

    }

    @Override
    public void showErrorMessage(String message, Throwable e) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

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
        } else {
            displayValue(value);
        }
    }

    private void displayValue(int x) {
        if(x == 1){
            mQuantity.setText(String.valueOf(1));
        } else{
            mQuantity.setText(String.valueOf(x));
        }
    }

    @Override
    public void showProductDetail(ProductDetailData productDetail) {

        //set text
        mNama.setText(productDetail.getName());
//        String price = "Rp " + productDetail.getProductDetailRentData().getPrice();
        Timber.d("harga %s", productDetail.getPrice());
        mHarga.setText(MainUtils.formatRupiah(productDetail.getPrice()));
        mDesc.setText(productDetail.getDescription());

        //get List
        colorList.addAll(productDetail.getColor());
        sizeList.addAll(productDetail.getSize());

        ArrayAdapter<String> adapterWarna =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        colorList);

        mColor.setText(colorList.get(0));
        mColor.setAdapter(adapterWarna);

        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        sizeList);

        mSize.setText(sizeList.get(0));
        mSize.setAdapter(adapterSize);

        //set slide
        Timber.d("gambar : %s", productDetail.getImages());
        mImages.setSliderAdapter(sliderAdapter);
        sliderAdapter.setImageUrl(productDetail.getImages());
        sliderAdapter.notifyDataSetChanged();
        mImages.setIndicatorAnimation(IndicatorAnimations.WORM);
        mImages.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

        mAddToCart.setOnClickListener(v ->
                presenter.addToCart(
                        productDetail.getId(),
                Integer.valueOf(
                        mQuantity.getText().toString()),
                        mSize.getText().toString(),
                        mColor.getText().toString(),
                        mHarga.getText().toString())
        );


        mAddFavorite.setOnClickListener(v -> {
            FavoriteDetailData favoriteDetailData = AppDatabase.checkFavorite(productDetail.getId());
            Timber.d("add favorite clicked");
            if (favoriteDetailData == null){
                presenter.addToFavorite(productDetail.getId(),
                        Integer.valueOf(
                                mQuantity.getText().toString()));
                Timber.d("add favorite like clicked");
            } else {
                presenter.removeFavorite(favoriteDetailData.getWishlistId());
                Timber.d("add favorite unlike clicked");
            }
            }
        );
    }

    @Override
    public void showProductRentDetail(ProductDetailRentData productDetailRentData) {
        mNama.setText(productDetailRentData.getName());
//        String price = "Rp " + productDetail.getProductDetailRentData().getPrice();
//        mHarga.setText(productDetailRentData.getPrice());
        mDesc.setText(productDetailRentData.getDescription());
        mDayWrapper.setVisibility(View.VISIBLE);
        mDay.setVisibility(View.VISIBLE);
        mHarga.setVisibility(View.GONE);


        //get List
        colorList.addAll(productDetailRentData.getColor());
        sizeList.addAll(productDetailRentData.getSize());

        ArrayAdapter<String> adapterWarna =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        colorList);

        mColor.setText(colorList.get(0));
        mColor.setAdapter(adapterWarna);

        ArrayAdapter<String> adapterSize =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        sizeList);

        mSize.setText(sizeList.get(0));
        mSize.setAdapter(adapterSize);

        priceList.addAll(productDetailRentData.getPrice());
        ArrayAdapter<String> adapterDurasi =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        priceList);

        mDay.setText(priceList.get(0));
        mDay.setAdapter(adapterDurasi);
        //set slide
        Timber.d("gambar : %s", productDetailRentData.getImages());
        mImages.setSliderAdapter(sliderAdapter);
        sliderAdapter.setImageUrl(productDetailRentData.getImages());
        sliderAdapter.notifyDataSetChanged();
        mImages.setIndicatorAnimation(IndicatorAnimations.WORM);
        mImages.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);

        mAddToCart.setOnClickListener(v ->
                presenter.addToCart(
                        productDetailRentData.getId(),
                        Integer.valueOf(
                                mQuantity.getText().toString()),
                        mSize.getText().toString(),
                        mColor.getText().toString(),
                        mDay.getText().toString())
        );


        mAddFavorite.setOnClickListener(v -> {
                    FavoriteDetailData favoriteDetailData = AppDatabase.checkFavorite(productDetailRentData.getId());
                    Timber.d("add favorite clicked");
                    if (favoriteDetailData == null){
                        presenter.addToFavorite(productDetailRentData.getId(),
                                Integer.valueOf(
                                        mQuantity.getText().toString()));
                        Timber.d("add favorite like clicked");
                    } else {
                        presenter.removeFavorite(favoriteDetailData.getWishlistId());
                        Timber.d("add favorite unlike clicked");
                    }
                }
        );
    }

    @Override
    public void showLoading() {
        showDialogLoading("Processing...");
    }

    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }
}
