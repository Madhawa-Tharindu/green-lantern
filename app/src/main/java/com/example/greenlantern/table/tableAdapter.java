package com.example.greenlantern.table;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.R;
import com.example.greenlantern.Table;

import java.io.Serializable;
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



        //for the update and delete
        holder.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context,holder.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                switch (menuItem.getItemId()){
                    case R.id.menu_edit:
                        Log.i("Update key",String.valueOf(table.getKey()));
                        Intent intent=new Intent(context, Update_form.class);

                        intent.putExtra("editKey", String.valueOf(table.getKey()));
                        intent.putExtra("name",table.getForName());
                        intent.putExtra("date",table.getReservedDate());
                        intent.putExtra("phone",table.getPhoneNum());
                        intent.putExtra("total",table.getTotal());
                        intent.putExtra("table",table.getTableNo());


                        context.startActivity(intent);
                        break;
                    //remove data
                    case R.id.menu_remove:


                        Log.i("Removekey",String.valueOf(table.getKey()));

                        


                        /*
                       DAOETable dao=new DAOETable();


                        dao.remove(table.getKey()).addOnSuccessListener(suc->
                        {
                            Toast.makeText(context, "Reservation is removed", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            list.remove(table);
                            System.exit(0);



                        }).addOnFailureListener(er->
                        {
                            Toast.makeText(context, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                        });

                         */

                        break;
                }
                return false;
            });
            popupMenu.show();
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,table,total,txt_option;


        public TableViewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.tvName);
            date=itemView.findViewById(R.id.tvdate);
            table=itemView.findViewById(R.id.tvtable);
            total = itemView.findViewById(R.id.tvtotal);
            txt_option=itemView.findViewById(R.id.txt_option);

        }
    }



}
