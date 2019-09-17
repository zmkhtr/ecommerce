package web.id.azammukhtar.subico.UI.HomeFragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import web.id.azammukhtar.subico.Adapter.CategoryAdapter;
import web.id.azammukhtar.subico.Adapter.SliderAdapter;
import web.id.azammukhtar.subico.Adapter.SliderHomeAdapter;
import web.id.azammukhtar.subico.Adapter.SubCategoryAdapter;
import web.id.azammukhtar.subico.BlankActivity;
import web.id.azammukhtar.subico.CheckoutActivity;
import web.id.azammukhtar.subico.MainMenuActivity;
import web.id.azammukhtar.subico.Model.Cart.Cartproduct;
import web.id.azammukhtar.subico.Model.Category.Category;
import web.id.azammukhtar.subico.Model.Product.Datum;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Model.SubCategory.SubCat;
import web.id.azammukhtar.subico.Model.SubCategory.SubCategory;
import web.id.azammukhtar.subico.Model.UserLogin.User;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.SearchActivity;
import web.id.azammukhtar.subico.UI.DetailActivity;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.LoginActivity.LoginActivity;
import web.id.azammukhtar.subico.UI.MainActivity.MainActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static web.id.azammukhtar.subico.MainMenuActivity.SCENARIO;
import static web.id.azammukhtar.subico.UI.DetailActivity.RENT_CODE;
import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeContract.View{
    private static final String TAG = "HomeFragment";
    public static final String SEARCH_QUERY = "query";

    private boolean requestOnWay = false;

    @BindView(R.id.imageHomeSlider)
    SliderView sliderView;
    private SliderHomeAdapter sliderAdapter;

    private PublishProcessor<Integer> pagination;

    private List<Datum> productModels = new ArrayList<>();
    private List<web.id.azammukhtar.subico.Model.Category.Product> categorytModels = new ArrayList<>();
    private List<SubCategory> subcategortModels = new ArrayList<>();
    private HomeFragmentAdapter homeFragmentAdapter;
    private CategoryAdapter categoryAdapter;
    private SubCategoryAdapter subCategoryAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.progressHomeLoading)
    AVLoadingIndicatorView progressBar;
    private Unbinder unbinder;
    private Context context;
    private String filterKey;

    @BindView(R.id.recyclerViewHome)
    RecyclerView recyclerView;

    @BindView(R.id.recyclerViewHomeCategories)
    RecyclerView recyclerViewCategory;

    @BindView(R.id.recyclerViewHomeSubCategories)
    RecyclerView recyclerViewSubCategory;
//
//    @BindView(R.id.buttonHomeAll)
//    Button buttonFilterAll;
//
//    @BindView(R.id.buttonHomePria)
//    Button buttonFilterPria;
//
//    @BindView(R.id.buttonHomeWanita)
//    Button buttonFilterWanita;
//
//    @BindView(R.id.searchViewHomeQuery)
//    EditText edtSearch;

    private HomeContract.Presenter presenter;


    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Frag : ", "home");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        unbinder = ButterKnife.bind(this, view);
        pagination = PublishProcessor.create();

        homeFragmentAdapter = new HomeFragmentAdapter(getContext());
        categoryAdapter = new CategoryAdapter();
        subCategoryAdapter = new SubCategoryAdapter();

        Log.d(TAG, "onViewCreated: " + productModels);
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeFragmentAdapter);

        LinearLayoutManager layoutManagerCategory = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManagerCategory);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setAdapter(categoryAdapter);

        LinearLayoutManager layoutManagerSubCategory = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSubCategory.setLayoutManager(layoutManagerSubCategory);
        recyclerViewSubCategory.setHasFixedSize(true);
        recyclerViewSubCategory.setAdapter(subCategoryAdapter);

        context = getContext();
        homeFragmentAdapter.setOnItemClickListener(position -> {
            Log.d(TAG, "onViewCreated: click " + position);
            Datum productModel = homeFragmentAdapter.getProductModels().get(position);
            Intent i = new Intent(getActivity(), DetailActivity.class);
            Log.d(TAG, "onViewCreated: " + productModel.getName() + productModel.getId());
            i.putExtra(ID_PRODUCT, productModel.getId());
            startActivity(i);
        });

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                web.id.azammukhtar.subico.Model.Category.Product category = categoryAdapter.getTitles().get(position);
                Intent i = new Intent(getActivity(), BlankActivity.class);
                Log.d(TAG, "onViewCreated: " + category.getName() + category.getId());
                i.putExtra(SCENARIO, category.getName());
                startActivity(i);
            }
        });

        subCategoryAdapter.setOnItemClickListener(new SubCategoryAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                SubCategory subCategory = subCategoryAdapter.getTitles().get(position);
                Intent i = new Intent(getActivity(), SearchActivity.class);
                Log.d(TAG, "onViewCreated: " + subCategory.getName() + subCategory.getId());
                i.putExtra("SUBCATEGORY", subCategory.getName());
                startActivity(i);
            }
        });

//        recyclerView.addOnScrollListener(new HomePagination(layoutManager) {
//            @Override
//            public void onLoadMore(int currentPage, int totalItemCount, View view) {
//                if (!ApiNetwork.isNetworkConnected(context)){
//                    Toast.makeText(getContext(), "Please, connect to internet", Toast.LENGTH_SHORT).show();
//                } else if (!requestOnWay && ApiNetwork.isNetworkConnected(context)) {
//                    pagination.onNext(currentPage + 1);
//                }
//            }
//        });

        getProduct();
        sliderAdapter = new SliderHomeAdapter(getContext());
        sliderView.setSliderAdapter(sliderAdapter);
        setSlider();
        getCategory();
        getSubCategory();

//        getProduct();
//        homeFragmentAdapter.setProductModels(AppDatabase.getProducts());
//        productModels.addAll(AppDatabase.getProducts());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.textHomeSell)
    void sell(){
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textHomeRent)
    void rent(){
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra(RENT_CODE, "RENT");
        startActivity(intent);
    }

    @OnClick(R.id.textHomePreOrder)
    void preOrder(){
        Intent intent = new Intent(getContext(), CheckoutActivity.class);
        startActivity(intent);
    }

    private void getCategory(){
        Call<Category> call = ApiNetwork.getApiInterface().getCategory("Bearer " + SessionManager.getInstance().getUserToken());
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if(response.isSuccessful()){
                    List<web.id.azammukhtar.subico.Model.Category.Product> title = response.body().getProduct();
                    categoryAdapter.setTitles(title);
                    Log.d(TAG, "onResponse: " + title);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    private void getSubCategory(){
        Call<SubCat> call = ApiNetwork.getApiInterface().getSubCategory("Bearer " + SessionManager.getInstance().getUserToken(), 2);
        call.enqueue(new Callback<SubCat>() {
            @Override
            public void onResponse(Call<SubCat> call, Response<SubCat> response) {
                if(response.isSuccessful()){
                    List<SubCategory> title = response.body().getSubCategories();
                    subCategoryAdapter.setTitles(title);
                    Log.d(TAG, "onResponse: " + title);
                }
            }

            @Override
            public void onFailure(Call<SubCat> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void setSlider(){
        List<String> listImageBanner = new ArrayList<>();

        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner1410873492541838945caed.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner_(580x240)_-_promo_FREE_UPSIZE_LED_TV.jpg");
        listImageBanner.add("https://www.hartonoelektronika.com/images/watermarked/promo/10/slider_banner_Promo_Ramadhan.jpg");

        sliderAdapter.setImageUrl(listImageBanner);
        sliderAdapter.notifyDataSetChanged();
        sliderView.startAutoCycle();
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
    }

    private void getProduct() {
        Disposable disposable = pagination.onBackpressureDrop()
                .doOnNext(integer -> {
                    requestOnWay = true;
                    progressBar.setVisibility(View.VISIBLE);
                })
                .concatMap(new Function<Integer, Publisher<Response<Product>>>() {
                    @Override
                    public Publisher<Response<Product>> apply(Integer fromId) throws Exception {
                        return getProductList(fromId);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Response<Product>>() {
                    @Override
                    public void accept(Response<Product> productResponse) throws Exception {
                        if (productResponse.isSuccessful()) {
                            homeFragmentAdapter.setProductModels(productResponse.body().getProduct().getData());
                        }
                        requestOnWay = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                })
                .doOnError(throwable -> {
                    if (throwable instanceof SocketTimeoutException) {
                        Response<?> response = ((HttpException) throwable).response();
                        Log.e(TAG, "Error" + response.message());
                    }
                })
                .subscribe();

        compositeDisposable.add(disposable);
        pagination.onNext(0);
    }


    private static Flowable<Response<Product>> getProductList(int page) {
        return ApiNetwork.getApiInterface().getProducts("Bearer " + SessionManager.getInstance().getUserToken(), page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public void showProgressBar() {
        requestOnWay = true;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        requestOnWay = false;
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onGetSuccess(String message) {

    }

    @Override
    public void onGetError(String message) {

    }
}

