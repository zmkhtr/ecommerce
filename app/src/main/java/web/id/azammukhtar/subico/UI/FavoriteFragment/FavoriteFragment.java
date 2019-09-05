package web.id.azammukhtar.subico.UI.FavoriteFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import web.id.azammukhtar.subico.Adapter.FavoriteFragmentAdapter;
import web.id.azammukhtar.subico.Database.AppDatabase;
import web.id.azammukhtar.subico.Model.Favorite.Datum;
import web.id.azammukhtar.subico.Model.Favorite.Favorite;
import web.id.azammukhtar.subico.Model.Product.Product;
import web.id.azammukhtar.subico.Model.ProductModel;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.UI.DetailActivity;
import web.id.azammukhtar.subico.UI.HomeFragment.HomeFragmentAdapter;
import web.id.azammukhtar.subico.UI.HomeFragment.HomePagination;
import web.id.azammukhtar.subico.UI.MainActivity.MainActivity;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.ID_PRODUCT;
import static web.id.azammukhtar.subico.UI.MainActivity.MainActivity.POST_KEY;


public class FavoriteFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "FavoriteFragment";
    public static final String FAVORITE_STATUS = "FAVORITE_STATUS";
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private FavoriteFragmentAdapter favoriteFragmentAdapter;

    private List<Datum> datumList = new ArrayList<>();

    private boolean requestOnWay = false;

    private PublishProcessor<Integer> pagination;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Context context;

    @BindView(R.id.progressHomeLoading)
    AVLoadingIndicatorView progressBar;

    @BindView(R.id.swipeRefreshFavorite)
    SwipeRefreshLayout swipeRefreshLayout;

    private Unbinder unbinder;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Frag : ", "favorite");
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        pagination = PublishProcessor.create();

        context = getContext();
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.recyclerViewFavorite);
        favoriteFragmentAdapter = new FavoriteFragmentAdapter(getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        favoriteFragmentAdapter.setOnItemClickListener(position -> {
            Log.d(TAG, "onViewCreated: " + position);
            Datum productModel = favoriteFragmentAdapter.getProductModels().get(position);
            Intent i = new Intent(getActivity(), DetailActivity.class);
            Log.d(TAG, "onViewCreated: " + productModel.getName() + productModel.getId());
            i.putExtra(ID_PRODUCT, productModel.getId());
            i.putExtra(FAVORITE_STATUS, true);
            startActivity(i);
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
        getFavorite();
        recyclerView.setAdapter(favoriteFragmentAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void getFavorite() {
        Log.d(TAG, "getFavorite: coba ");
        Call<Favorite> call = ApiNetwork.getApiInterface().getFavorite("Bearer " + SessionManager.getInstance().getUserToken(), 1);
        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                Log.d(TAG, "onResponse: coba " + response.code());
                if (response.isSuccessful()) {
                    datumList.clear();
                    datumList.addAll(response.body().getWishlists().getData());
                    favoriteFragmentAdapter.setProductModels(datumList);
                }
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                Log.e(TAG, "onFailure: coba ", t);
            }
        });
    }
//    private void getFavorite() {
//        Log.d(TAG, "getFavorite: execute ");
//        Disposable disposable = pagination.onBackpressureDrop()
//                .doOnNext(integer -> {
//                    requestOnWay = true;
////                    progressBar.setVisibility(View.VISIBLE);
//                })
//                .concatMap(new Function<Integer, Publisher<Response<Favorite>>>() {
//                    @Override
//                    public Publisher<Response<Favorite>> apply(Integer page) throws Exception {
//                        return getFavoriteResponse(page);
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext(new Consumer<Response<Favorite>>() {
//                    @Override
//                    public void accept(Response<Favorite> favoriteResponse) throws Exception {
//                        if (favoriteResponse.isSuccessful()) {
//                            Log.d(TAG, "accept: execute success ");
////                            favoriteFragmentAdapter.setProductModels(favoriteResponse.body().getWishlists().getData());
//
//                            datumList.addAll(favoriteResponse.body().getWishlists().getData());
//                            favoriteFragmentAdapter.setProductModels(datumList);
////                            AppDatabase.saveWhistlist(favoriteResponse.body().getWishlists().getData());
//                        }
//                        requestOnWay = false;
////                        progressBar.setVisibility(View.INVISIBLE);
//                    }
//                })
//                .doOnError(throwable -> {
//                    if (throwable instanceof SocketTimeoutException) {
//                        Log.d(TAG, "accept: execute fail ");
//                        Response<?> response = ((HttpException) throwable).response();
//                        Log.e(TAG, "Error" + response.message());
//                    }
//                })
//                .subscribe();
//
//        compositeDisposable.add(disposable);
//        pagination.onNext(0);
//    }
//
//    private static Flowable<Response<Favorite>> getFavoriteResponse(int page) {
//        return ApiNetwork.getApiInterface().getFavorite("Bearer " + SessionManager.getInstance().getUserToken(), page)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.search);
        if (item != null)
            item.setVisible(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onRefresh() {
        getFavorite();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }


}
