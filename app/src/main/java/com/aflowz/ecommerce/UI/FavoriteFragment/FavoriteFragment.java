package com.aflowz.ecommerce.UI.FavoriteFragment;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aflowz.ecommerce.Base.BaseFragment;
import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.LocalDatabase.AppRepository;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseFavorite.FavoriteResponse;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.DetailActivity.DetailActivity;
import com.aflowz.ecommerce.Utils.MainUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.PRODUCT_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.QUERY_KEY;
import static com.aflowz.ecommerce.UI.ProductActivity.ProductActivity.SUB_CATEGORY_KEY;
import static com.aflowz.ecommerce.Utils.Constant.PRODUCT_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private FavoriteAdapter favoriteAdapter;

    @BindView(R.id.recyclerViewFavorite)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshFavorite)
    SwipeRefreshLayout swipeRefreshLayout;

    //    @BindView(R.id.textFavoriteEmptyItem)
    private TextView mEmpty;

    private AppRepository appRepository = new AppRepository();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mEmpty = view.findViewById(R.id.textFavoriteEmptyItem);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(this);
        favoriteAdapter = new FavoriteAdapter(getContext());

        favoriteAdapter.setOnItemClickListener(v -> {
                    Timber.d("product clicked %s", v);

                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    intent.putExtra(PRODUCT_ID, v.getId());
                    Timber.d("Jenis order %s", v.getJenisOrder());
                    if(v.getJenisOrder().equals("PEMBELIAN")){
                        intent.putExtra(PRODUCT_KEY, "SALE");
                    } else {
                        intent.putExtra(PRODUCT_KEY, "RENT");
                    }
                    startActivity(intent);
                }
                );
        setRecyclerView();

    }



    @Override
    public void onResume() {
        setUserVisibleHint(true);
        super.onResume();
    }


    private void checkIfEmpty() {
        setRecyclerView();
        Timber.d("tt %s", favoriteAdapter.getItemCount());
        if (favoriteAdapter.getItemCount() == 0) {
            mEmpty.setVisibility(View.VISIBLE);
            Timber.d("tt Is empty");
        } else {
            mEmpty.setVisibility(View.INVISIBLE);
            Timber.d("tt Is not empty");
        }
    }





    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //do when hidden
            Timber.d("tt invisible");
            onRefresh();
        } else {
            //do when show
            Timber.d("tt visible");
            onRefresh();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void setRecyclerView() {
        updateFavorite();
        GridLayoutManager layoutManagerProduct = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(favoriteAdapter);
    }


    @Override
    public void onRefresh() {
        checkIfEmpty();
        new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
    }

    private void updateFavorite() {
        ApiNetwork.getApiInterface().getFavorite()
                .enqueue(new Callback<FavoriteResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<FavoriteResponse> call, @NotNull Response<FavoriteResponse> response) {
                        if (response.isSuccessful()){
                            if (response.body() != null) {
                                favoriteAdapter.submitList(response.body().getWishlists());
                                if (!response.body().getWishlists().isEmpty()) {
                                    mEmpty.setVisibility(View.INVISIBLE);
                                    Timber.d("response tak kosong");
                                } else if (response.body().getWishlists().isEmpty()){
                                    favoriteAdapter.clearList();
                                    mEmpty.setVisibility(View.VISIBLE);
                                    Timber.d("response kosong");
                                }
                            }
                            MainUtils.logSuccessMessage("Success get Favorite");
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<FavoriteResponse> call,@NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get Favorite ", t);
                    }
                });
    }
}
