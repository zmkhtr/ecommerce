package com.aflowz.ecommerce.UI.History;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aflowz.ecommerce.Base.BaseFragment;
import com.aflowz.ecommerce.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends BaseFragment {
    @BindView(R.id.cardView1)
    CardView cardView1;
    @BindView(R.id.cardView2)
    CardView cardView2;
    @BindView(R.id.cardView3)
    CardView cardView3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @OnClick(R.id.orderContainer)
    void onClick(){
        startActivity(new Intent(getContext(), DetailHistoryActivity.class));
    }
    @OnClick(R.id.cardView1)
    void cardClick1(){
        startActivity(new Intent(getContext(), DetailHistoryActivity.class));
    }
    @OnClick(R.id.cardView2)
    void cardClick2(){
        startActivity(new Intent(getContext(), DetailHistoryActivity.class));
    }
    @OnClick(R.id.cardView3)
    void cardClick3(){
        startActivity(new Intent(getContext(), DetailHistoryActivity.class));
    }

}
