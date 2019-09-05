package web.id.azammukhtar.subico.UI.TransactionFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import web.id.azammukhtar.subico.Adapter.TransactionFragmentAdapter;
import web.id.azammukhtar.subico.Model.TransactionModel;
import web.id.azammukhtar.subico.R;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class TransactionFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private List<TransactionModel> productModels = new ArrayList<>();
    private TransactionFragmentAdapter cartFragmentAdapter;

    public TransactionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Frag : ", "favorite");
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewTransaction);
        cartFragmentAdapter = new TransactionFragmentAdapter(getContext());
        productModels = new ArrayList<>();

        populateModel();
        Log.d(TAG, "onViewCreated: " + productModels);
        cartFragmentAdapter.setProductModels(productModels);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(cartFragmentAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        cartFragmentAdapter.setOnItemClickListener(position -> {
            Log.d(TAG, "onViewCreated: " + position);
        });
    }

    private void populateModel(){
        productModels.add(new TransactionModel("#Order2039859438", "Status : Delivered"));
        productModels.add(new TransactionModel("#Order5668230438", "Status : On Process"));
        productModels.add(new TransactionModel("#Order9843534823", "Status : On Process"));
        productModels.add(new TransactionModel("#Order5698508698", "Status : Delivered"));
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
}
