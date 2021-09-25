package com.example.greenlantern.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.greenlantern.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private int[] images;
    private Context context;


    public SliderAdapter(int[] images) {
        this.images = images;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_food_imageslider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        viewHolder.imageview.setImageResource(images[position]);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageview;

        public SliderAdapterVH(View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.imageview);
            this.itemView = itemView;
        }
    }

}
