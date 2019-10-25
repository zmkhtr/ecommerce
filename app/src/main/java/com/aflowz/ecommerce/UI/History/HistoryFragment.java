package com.aflowz.ecommerce.UI.History;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aflowz.ecommerce.Base.BaseFragment;
import com.aflowz.ecommerce.Network.ApiNetwork;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseOrder.OrderResponse;
import com.aflowz.ecommerce.R;
import com.aflowz.ecommerce.UI.FavoriteFragment.FavoriteAdapter;
import com.aflowz.ecommerce.Utils.MainUtils;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.ADMIN_KEY;
import static com.aflowz.ecommerce.Utils.Constant.ORDER_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private HistoryAdapter historyAdapter;

    @BindView(R.id.recyclerViewHistory)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshHistory)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.textHistoryEmptyItem)
    TextView mEmpty;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        historyAdapter = new HistoryAdapter();
        getData();
        setRecyclerView();
    }

    private void setRecyclerView(){
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getContext());
        layoutManagerProduct.setStackFromEnd(true);
        layoutManagerProduct.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setHasFixedSize(true);
        recyclerView.smoothScrollToPosition(historyAdapter.getItemCount());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManagerProduct.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(historyAdapter);

        historyAdapter.setOnItemClickListener(order -> {
            Intent intent = new Intent(getActivity(), DetailHistoryActivity.class);
            intent.putExtra(ADMIN_KEY, false);
            intent.putExtra(ORDER_ID, order.getId());
            startActivity(intent);
        });
    }

    private void getData(){
        ApiNetwork.getApiInterface().getOrder()
                .enqueue(new Callback<OrderResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<OrderResponse> call, @NotNull Response<OrderResponse> response) {
                        if (response.isSuccessful()){
                            historyAdapter.submitList(response.body().getOrder());
                            MainUtils.logSuccessMessage("Successful get list order");
                        }
                        MainUtils.logSuccessMessage("Success get list order");
                    }

                    @Override
                    public void onFailure(@NotNull Call<OrderResponse> call, @NotNull Throwable t) {
                        MainUtils.logErrorMessage("Error get list order", t);
                    }
                });
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
    public void onResume() {
        setUserVisibleHint(true);
        super.onResume();
    }

    private void checkIfEmpty() {
        setRecyclerView();
        Timber.d("tt %s", historyAdapter.getItemCount());
        if (historyAdapter.getItemCount() == 0) {
            mEmpty.setVisibility(View.VISIBLE);
            Timber.d("tt Is empty");
        } else {
            mEmpty.setVisibility(View.INVISIBLE);
            Timber.d("tt Is not empty");
        }
    }
    @Override
    public void onRefresh() {
        checkIfEmpty();
        new Handler().postDelayed(() -> swipeRefreshLayout.setRefreshing(false), 2000);
    }
}
