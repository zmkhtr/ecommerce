package com.aflowz.ecommerce.UI.MainFragment.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aflowz.ecommerce.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class SliderMiniAdapter extends SliderViewAdapter<SliderMiniAdapter.ViewHolder> {
    private Context context;
    private List<String> imageUrl = new ArrayList<>();

    public SliderMiniAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider_small, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String url = imageUrl.get(position);
        Timber.d("onBindViewHolder: slider %s", url);
        Glide.with(context).load(url).into(viewHolder.mImage);
    }


    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return imageUrl.size();
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {
        @BindView(R.id.imageSliderSmall)
        ImageView mImage;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}

