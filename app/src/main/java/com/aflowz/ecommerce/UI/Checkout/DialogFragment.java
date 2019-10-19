package com.aflowz.ecommerce.UI.Checkout;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aflowz.ecommerce.LocalDatabase.AppDatabase;
import com.aflowz.ecommerce.Network.ResponseModel.ResponseCart.CartDetailData;
import com.aflowz.ecommerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends android.app.DialogFragment {

    @BindView(R.id.recyclerViewCart)
    RecyclerView recyclerView;

    private CheckoutAdapter cartAdapter;

    private Unbinder unbinder;

    private void setOnClick() {
        cartAdapter.setOnItemClickListener(new CheckoutAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Timber.d("clicked");
            }

            @Override
            public void onRemoveClick(CartDetailData cartDetailData) {
                Timber.d("clicked");
            }

            @Override
            public void onBindViewHolder(CheckoutAdapter.ViewHolder holder) {
                Timber.d("clicked");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        cartAdapter = new CheckoutAdapter(getContext());
        setRecyclerView();
        setOnClick();
    }

    private void setRecyclerView() {
        cartAdapter.submitList(AppDatabase.getCart());
        Timber.d("Cart adapter %s", cartAdapter.getItemCount());
        LinearLayoutManager layoutManagerProduct = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManagerProduct);
        recyclerView.setHasFixedSize(true);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManagerProduct.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(cartAdapter);
    }


}
