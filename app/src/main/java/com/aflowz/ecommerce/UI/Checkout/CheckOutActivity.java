package com.aflowz.ecommerce.UI.Checkout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCheckout.LocalCheckoutResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseProfile.ProfileUserData;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CityResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CostResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.CourierResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.DistrictResponse;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseRajaOngkir.ProvinceDetailResponse;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.Utils.MainUtils;
import com.aflowz.ecommerce.Utils.SessionManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.CHECKOUT_KEY;
import static com.aflowz.ecommerce.Utils.Constant.DETAIL_CHECKOUT;
import static com.aflowz.ecommerce.Utils.Constant.LOCAL;

public class CheckOutActivity extends BaseActivity {

    @BindView(R.id.edtCheckoutName)
    EditText mName;
    @BindView(R.id.edtCheckoutPhone)
    EditText mPhone;
    @BindView(R.id.edtCheckoutAddress)
    EditText mAddress;
    @BindView(R.id.dropdownCheckoutCity)
    AutoCompleteTextView mCity;
    @BindView(R.id.dropdownCheckoutProvince)
    AutoCompleteTextView mProvince;
    @BindView(R.id.dropdownCheckoutDistrict)
    AutoCompleteTextView mDistrict;
    @BindView(R.id.dropdownCheckoutCourier)
    AutoCompleteTextView mCourier;
    @BindView(R.id.dropdownCheckoutCost)
    AutoCompleteTextView mCost;

    @BindView(R.id.textCheckoutOngkir)
    TextView mOngkir;
    @BindView(R.id.textCheckoutTotal)
    TextView mTotal;



    private String keyCourier;
    private String etd;
    private int ongkir;
    private int province;
    private int city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        ButterKnife.bind(this);


        setUpButton();
        setAppBarTitle(getString(R.string.local_checkout));
        setDropdown();
        String totalPrice = "Total price : " + MainUtils.formatRupiah(SessionManager.getInstance().getPrice());
        mTotal.setText(totalPrice);

        ProfileUserData profileUserData = AppDatabase.getProfile(SessionManager.getInstance().getUserName());
        mName.setText(profileUserData.getName());
        mPhone.setText(profileUserData.getPhone());
        mAddress.setText(profileUserData.getAddress());
    }

    @OnClick(R.id.btnCheckoutCart)
    void checkCart(){
        bottomDialogFragment();
    }
    private void bottomDialogFragment() {
        DialogFragment myDialog = new DialogFragment();
        myDialog.show(getFragmentManager().beginTransaction().addToBackStack("UpdateFragment"), "UpdateFragment");
    }

    private void setDropdown() {
        /* PROVINCE */
        List<ProvinceDetailResponse> provinceResponses = AppDatabase.getProvince();

        List<String> provinceList = new ArrayList<>();
        for (int i = 0; i < provinceResponses.size(); i++) {
            provinceList.add(provinceResponses.get(i).getProvince());
        }

        ArrayAdapter<String> adapterProvince =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        provinceList);

//        mProvince.setText(adapterProvince.getItem(0));
        mProvince.setAdapter(adapterProvince);

        mProvince.setOnItemClickListener((adapterView, view, i, l) -> {
            getCity(Integer.valueOf(provinceResponses.get(i).getProvinceId()));
            province = Integer.valueOf(provinceResponses.get(i).getProvinceId());
        });


        /* COURIER */
        List<CourierResponse> courierResponses = AppDatabase.getCourier();

        List<String> courierList = new ArrayList<>();
        for (int i = 0; i < courierResponses.size(); i++) {
            courierList.add(courierResponses.get(i).getNamaKurir());
        }

        ArrayAdapter<String> adapterCourier =
                new ArrayAdapter<>(
                        getBaseContext(),
                        R.layout.dropdown_item,
                        courierList);

        mCourier.setText(adapterCourier.getItem(0));
        mCourier.setAdapter(adapterCourier);

        keyCourier = courierResponses.get(0).getKeyKurir();

        mCourier.setOnItemClickListener((adapterView, view, i, l) -> {
            keyCourier = courierResponses.get(i).getKeyKurir();
            getCost(city, keyCourier);
            Timber.d("setDropdown: %s", keyCourier);
        });
    }

    private void getCity(int idProvince) {
        showDialogLoading("Loading...");
        ApiNetwork.getApiInterface().getCity(idProvince)
                .enqueue(new Callback<List<CityResponse>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<CityResponse>> call, @NotNull Response<List<CityResponse>> response) {

                        if (response.isSuccessful()) {
                            MainUtils.logSuccessMessage("Success get City");
                            List<String> cityList = new ArrayList<>();
                            for (int i = 0; i < response.body().size(); i++) {
                                cityList.add(response.body().get(i).getCityName());
                            }

                            ArrayAdapter<String> adapterCity =
                                    new ArrayAdapter<>(
                                            getBaseContext(),
                                            R.layout.dropdown_item,
                                            cityList);

//                            mCity.setText(adapterCity.getItem(0));
                            mCity.setAdapter(adapterCity);


                            city = Integer.valueOf(response.body().get(0).getCityId());

                            mCity.setOnItemClickListener((adapterView, view, i, l) -> {
                                getDistrict(Integer.valueOf(response.body().get(i).getCityId()));
                                city = Integer.valueOf(response.body().get(i).getCityId());
                            });
                        }
                        dismissDialogLoading();
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<CityResponse>> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get City ", t);
                        dismissDialogLoading();
                    }
                });
    }

    private void getDistrict(int idCity) {
        showDialogLoading("Loading...");
        ApiNetwork.getApiInterface().getKecamatan(idCity)
                .enqueue(new Callback<List<DistrictResponse>>() {
                    @Override
                    public void onResponse(@NotNull Call<List<DistrictResponse>> call, @NotNull Response<List<DistrictResponse>> response) {
                        if (response.isSuccessful()) {
                            MainUtils.logSuccessMessage("Success get Kecamatan");
                            List<String> districtList = new ArrayList<>();
                            for (int i = 0; i < response.body().size(); i++) {
                                districtList.add(response.body().get(i).getSubdistrictName());
                            }

                            ArrayAdapter<String> adapterDistrict =
                                    new ArrayAdapter<>(
                                            getBaseContext(),
                                            R.layout.dropdown_item,
                                            districtList);

//                            mDistrict.setText(adapterDistrict.getItem(0));
                            mDistrict.setAdapter(adapterDistrict);


                            mDistrict.setOnItemClickListener((adapterView, view, i, l) -> {
                                getCost(Integer.valueOf(response.body().get(i).getCityId()), keyCourier);
                                Timber.d("idCity  %s", Integer.valueOf(response.body().get(i).getCityId()));
                            });
                        }
                        dismissDialogLoading();
                    }

                    @Override
                    public void onFailure(@NotNull Call<List<DistrictResponse>> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Kecamatan ", t);
                        dismissDialogLoading();
                    }
                });
    }

    private void getCost(int idCityNya, String keyCourier) {
        showDialogLoading("Loading...");
        Timber.d("idCity %s", idCityNya);
        ApiNetwork.getApiInterface().getCost(idCityNya, keyCourier)
                .enqueue(new Callback<CostResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<CostResponse> call, @NotNull Response<CostResponse> response) {
                        if (response.isSuccessful()) {
                            MainUtils.logSuccessMessage("Success get cost");
                            List<String> costList = new ArrayList<>();
                            for (int i = 0; i < response.body().getCosts().size(); i++) {
                                costList.add(response.body().getCosts().get(i).getService());
                            }

                            ArrayAdapter<String> adapterCost =
                                    new ArrayAdapter<>(
                                            getBaseContext(),
                                            R.layout.dropdown_item,
                                            costList);

//                            mCost.setText(adapterCost.getItem(0));
                            mCost.setAdapter(adapterCost);


                            mCost.setOnItemClickListener((adapterView, view, i, l) -> {
                                ongkir = response.body().getCosts().get(i).getCost().get(0).getValue();
                                etd = response.body().getCosts().get(i).getCost().get(0).getEtd();
                                Timber.d("ongkir %s", ongkir);
                                Timber.d("etd %s", etd);
                                int total = Integer.valueOf(SessionManager.getInstance().getPrice()) + response.body().getCosts().get(i).getCost().get(0).getValue();
                                String price = "Total price : " + MainUtils.formatRupiah(String.valueOf(total));
                                String ongkirLabel = "Ongkos kirim : " + MainUtils.formatRupiah(String.valueOf(ongkir));
                                mOngkir.setText(ongkirLabel);
                                mTotal.setText(price);
                            });
                        }
                        dismissDialogLoading();
                    }

                    @Override
                    public void onFailure(@NotNull Call<CostResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get cost ", t);
                        dismissDialogLoading();
                    }
                });
    }

    @OnClick(R.id.btnCheckoutConfirm)
    void checkOut() {
        String shippingMethod = mCourier.getText().toString() + " " + ongkir + " " + etd + " Hari";
        if (mName.getText().toString().trim().isEmpty() ||
                mAddress.getText().toString().trim().isEmpty() ||
                mPhone.getText().toString().trim().isEmpty() ||
                mProvince.getText().toString().isEmpty() ||
                mCourier.getText().toString().isEmpty() ||
                mCity.getText().toString().isEmpty() ||
                mDistrict.getText().toString().isEmpty() ||
                mCost.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.fill_blank, Toast.LENGTH_SHORT).show();
        }

        showDialogLoading("Loading...");
        ApiNetwork.getApiInterface()
                .checkOutLocal(
                        SessionManager.getInstance().getPrice(),
                        mCourier.getText().toString(),
                        String.valueOf(ongkir),
                        shippingMethod,
                        "LOCAL",
                        String.valueOf(province),
                        String.valueOf(city),
                        mAddress.getText().toString()
                )
                .enqueue(new Callback<LocalCheckoutResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<LocalCheckoutResponse> call, @NotNull Response<LocalCheckoutResponse> response) {
                        MainUtils.logSuccessMessage("Success get checkout");
                        dismissDialogLoading();
                        if (response.isSuccessful()){
                            Toast.makeText(CheckOutActivity.this, R.string.checkout_success, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CheckOutActivity.this, TransferActivity.class);
                            intent.putExtra(DETAIL_CHECKOUT,response.body());
                            intent.putExtra(CHECKOUT_KEY, LOCAL);
                            startActivity(intent);
                            finishAffinity();
                        }
                        Toast.makeText(CheckOutActivity.this, R.string.checkout_error, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NotNull Call<LocalCheckoutResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get checkout ", t);
                        Toast.makeText(CheckOutActivity.this, R.string.checkout_error, Toast.LENGTH_SHORT).show();
                        dismissDialogLoading();
                    }
                });
    }
}
