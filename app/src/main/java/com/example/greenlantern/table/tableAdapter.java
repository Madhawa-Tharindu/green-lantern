package com.example.greenlantern.table;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.R;

import java.util.ArrayList;

public class tableAdapter extends RecyclerView.Adapter<tableAdapter.TableViewHolder> {

    Context context;
    ArrayList<TableD> list;

    public tableAdapter(Context context, ArrayList<TableD> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new TableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        TableD table =list.get(position);
        holder.name.setText(table.getForName());
        holder.date.setText(table.getReservedDate());
        holder.total.setText(table.getTotal());
        holder.table.setText(table.getTableNo());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,table,total;


        public TableViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            date=itemView.findViewById(R.id.tvdate);
            table=itemView.findViewById(R.id.tvtable);
            total = itemView.findViewById(R.id.tvtotal);
        }
    }
}
