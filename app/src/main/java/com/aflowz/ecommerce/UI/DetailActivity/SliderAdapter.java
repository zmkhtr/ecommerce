package com.aflowz.ecommerce.UI.DetailActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aflowz.ecommerce.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.aflowz.ecommerce.Utils.Constant.IMG_URL;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {
    private Context context;
    private List<String> imageUrl = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String url = imageUrl.get(position);
        Timber.d("onBindViewHolder: slider gg %s", url);
        Glide.with(context).load(IMG_URL + url).into(viewHolder.mImage);
        Timber.d("Url gg " + IMG_URL + url);
    }


    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return imageUrl.size();
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {
        @BindView(R.id.imageSlider)
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

