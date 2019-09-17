package web.id.azammukhtar.subico.UI.HomeFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.avi.AVLoadingIndicatorView;

import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Response;
import web.id.azammukhtar.subico.Model.Product.Datum;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.DetailActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;

public class HomeWanitaFragment extends Fragment {
    private static final String TAG = "HomeWanitaFragment";

    private boolean requestOnWay = false;

    private PublishProcessor<Integer> pagination;

    private List<Datum> productModels = new ArrayList<>();
    private HomeFragmentAdapter homeFragmentAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @BindView(R.id.progressHomeLoading)
    AVLoadingIndicatorView progressBar;
    private Unbinder unbinder;
    private RecyclerView recyclerView;
    private Context context;

    @BindView(R.id.textFavoriteTitle)
    TextView textTitle;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Frag : ", "home");
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        pagination = PublishProcessor.create();
        recyclerView = view.findViewById(R.id.recyclerViewFavorite);
        homeFragmentAdapter = new HomeFragmentAdapter(getContext());

        textTitle.setText("Woman");

        Log.d(TAG, "onViewCreated: " + productModels);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(homeFragmentAdapter);
        context = getContext();
        homeFragmentAdapter.setOnItemClickListener(position -> {
            Log.d(TAG, "onViewCreated: click " + position);
            Datum productModel = homeFragmentAdapter.getProductModels().get(position);
            Intent i = new Intent(getActivity(), DetailActivity.class);
            Log.d(TAG, "onViewCreated: " + productModel.getName() + productModel.getId());
            i.putExtra(ID_PRODUCT, productModel.getId());
            startActivity(i);
        });

        recyclerView.addOnScrollListener(new HomePagination(layoutManager) {
            @Override
            public void onLoadMore(int currentPage, int totalItemCount, View view) {
                if (!ApiNetwork.isNetworkConnected(context)) {
                    Toast.makeText(getContext(), "Please, connect to internet", Toast.LENGTH_SHORT).show();
                } else if (!requestOnWay && ApiNetwork.isNetworkConnected(context)) {
                    pagination.onNext(currentPage + 1);
                }
            }
        });
        getProduct();
    }

    private void filter(String key){
        homeFragmentAdapter.getFilter().filter(key);
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
//                        homeFragmentAdapter.clearList(productResponse.body().getProduct().getData());
                        if (productResponse.isSuccessful()) {
                            homeFragmentAdapter.setProductModels(productResponse.body().getProduct().getData());
                            filter("catWoman");
//                            productModels.addAll(productResponse.body().getProduct().getData());
//                            homeFragmentAdapter.setProductModels(productModels);
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
}
