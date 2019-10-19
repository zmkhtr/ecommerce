package com.aflowz.ecommerce.UI.History;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aflowz.ecommerce.Base.BaseActivity;
import com.aflowz.ecommerce.R;
import com.anton46.stepsview.StepsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailHistoryActivity extends BaseActivity {

    @BindView(R.id.stepsView)
    StepsView mStepsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        ButterKnife.bind(this);
        String[] steps = {"Deterima", "Diproses", "Dikirim", "Selesai"};
//        List<String> steps = new ArrayList<>()
        mStepsView.setLabels(steps)
                .setBarColorIndicator(getResources().getColor(R.color.black))
                .setProgressColorIndicator(getResources().getColor(R.color.colorAccent))
                .setLabelColorIndicator(getResources().getColor(R.color.colorAccent))
                .setCompletedPosition(1)
                .drawView();

        setAppBarTitle("Order History Detail");
        setUpButton();
    }
}
