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

public class FoodOderViewAdapter extends RecyclerView.Adapter<FoodOderViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FoodOrder_Model> shopItemModelArrayList;
    private onItemClickListner mlistner;

    public FoodOderViewAdapter(Context context, ArrayList<FoodOrder_Model> shopItemModelArrayList) {
        this.context = context;
        this.shopItemModelArrayList = shopItemModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_foodorderview,parent,false);
        return new FoodOderViewAdapter.ViewHolder(view,mlistner);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FoodOrder_Model product = shopItemModelArrayList.get(position);
        float tot = product.getTotal();
        holder.name.setText(product.getPname());
        holder.price.setText("LKR."+tot);
        holder.date.setText(product.getDate());
        holder.note.setText(product.getNote());
    }

    @Override
    public int getItemCount() {
        return shopItemModelArrayList.size();
    }

    public void updateData(ArrayList<FoodOrder_Model> foodOrder_modelArrayList){
        shopItemModelArrayList.clear();
        shopItemModelArrayList.addAll(foodOrder_modelArrayList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView shopcardview;
        TextView name,price,date,note;
        ImageView image;

        public ViewHolder(@NonNull View itemView,onItemClickListner clickListener) {
            super(itemView);

            image = itemView.findViewById(R.id.delete);
            name  = itemView.findViewById(R.id.foodname);
            price = itemView.findViewById(R.id.foodprice);
            date = itemView.findViewById(R.id.date);
            note = itemView.findViewById(R.id.note);


            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mlistner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mlistner.onItemDelete(position);

                        }
                    }
                }
            });
        }
    }

    public interface onItemClickListner{
        void onItemDelete(int position);
    }
    public void setOnItemClickListner(onItemClickListner listner){
        mlistner = listner;
    }
}
