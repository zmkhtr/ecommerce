package web.id.azammukhtar.subico.UI.CartFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import web.id.azammukhtar.subico.Adapter.CartFragmentAdapter;
import web.id.azammukhtar.subico.Model.Cart.Cart;
import web.id.azammukhtar.subico.Model.Cart.Cartproduct;
import web.id.azammukhtar.subico.Model.Cart.Datum;
import web.id.azammukhtar.subico.Network.ApiNetwork;
import web.id.azammukhtar.subico.R;
import web.id.azammukhtar.subico.Utils.SessionManager;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class CartFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<Datum> productModels = new ArrayList<>();
    private CartFragmentAdapter cartFragmentAdapter;
    private Unbinder unbinder;

    @BindView(R.id.buttonCheckOut)
    AppCompatButton button;

    @BindView(R.id.swipeRefreshCart)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Frag : ", "Cart");
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        recyclerView = view.findViewById(R.id.recyclerViewCart);
        cartFragmentAdapter = new CartFragmentAdapter(getContext());

        swipeRefreshLayout.setOnRefreshListener(this);
        Log.d(TAG, "onViewCreated: " + productModels);
//        cartFragmentAdapter.setProductModels(productModels);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cartFragmentAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


//        cartFragmentAdapter.setOnItemClickListener(position -> {
//            Log.d(TAG, "onViewCreated: " + position);
//            Datum productModel = productModels.get(position);
//            Intent i = new Intent(getActivity(), DetailActivity.class);
//            i.putExtra(POST_KEY, product);
//            startActivity(i);
//        });
        getCart();
        cartFragmentAdapter.setOnItemClickListener(new CartFragmentAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onItemClick: clicked");
            }

            @Override
            public void onRemoveClick(String cartId) {
                deleteCart(cartId);
            }
        });
    }

    private void getCart(){
        Call<Cartproduct> call = ApiNetwork.getApiInterface().getCart("Bearer " + SessionManager.getInstance().getUserToken());
        call.enqueue(new Callback<Cartproduct>() {
            @Override
            public void onResponse(Call<Cartproduct> call, Response<Cartproduct> response) {
                if (response.isSuccessful()){
                    productModels.clear();
                    productModels.addAll(response.body().getCart().getData());
                    cartFragmentAdapter.setProductModels(productModels);
                    calculatePrice();
                }
            }

            @Override
            public void onFailure(Call<Cartproduct> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    
    private void deleteCart(String id){
        Call<Cartproduct> call = ApiNetwork.getApiInterface().deleteCart("Bearer " + SessionManager.getInstance().getUserToken(), id);
        call.enqueue(new Callback<Cartproduct>() {
            @Override
            public void onResponse(Call<Cartproduct> call, Response<Cartproduct> response) {
                Toast.makeText(getContext(), "Berhasil hapus cart", Toast.LENGTH_SHORT).show();
                getCart();
                calculatePrice();
                Log.d(TAG, "onResponse: " + response);
            }

            @Override
            public void onFailure(Call<Cartproduct> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal hapus cart", Toast.LENGTH_SHORT).show();
                getCart();
                calculatePrice();
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void calculatePrice(){
        int totalPrice = 0;
        for (int i = 0; i<productModels.size(); i++)
        {
            totalPrice += Integer.parseInt(productModels.get(i).getPrice());
        }

        String price = "Checkout : Rp " + totalPrice;
        button.setText(price);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item=menu.findItem(R.id.search);
        if(item!=null)
            item.setVisible(false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onRefresh() {
        getCart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
