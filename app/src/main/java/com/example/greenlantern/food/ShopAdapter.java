package com.example.greenlantern.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.greenlantern.R;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ShopItemModel> shopItemModelArrayList;
    private onItemClickListner mlistner;

    public ShopAdapter(Context context, ArrayList<ShopItemModel> shopItemModelArrayList) {
        this.context = context;
        this.shopItemModelArrayList = shopItemModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_food_recycler_layout,parent,false);
        return new ShopAdapter.ViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShopItemModel product = shopItemModelArrayList.get(position);

        holder.name.setText(product.getFoodname());
        holder.price.setText("LKR. "+product.getFoodprice());

        Glide.with(context)
                .load(product.getImage())
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return shopItemModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView shopcardview;
        TextView name,price;
        ImageView image;
        public ViewHolder(@NonNull View itemView, onItemClickListner clickListener) {
            super(itemView);

            shopcardview = itemView.findViewById(R.id.shopCardview);
            image = itemView.findViewById(R.id.foodimage);
            name = itemView.findViewById(R.id.foodname);
            price = itemView.findViewById(R.id.foodprice);

            shopcardview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mlistner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistner.onCardClick(position);

                        }
                    }
                }
            });

        }
    }


    public interface onItemClickListner{
        void onCardClick(int position);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        mlistner = listner;
    }
}
