package com.example.greenlantern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    //initialize variable

    Activity activity;
    ArrayList<String> arrayList;

    //constructor
    public MainAdapter(Activity activity,ArrayList<String> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //initialize variable
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawer_main,parent,false);

        //return view
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set text on text view
        holder.textView.setText(arrayList.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                int position = holder.getAdapterPosition();

                //redirect to homepage when position equal 0
                switch (position){
                    case 0:
                        activity.startActivity(new Intent(activity,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;

                    case 1:
                        //when position 1
                        //redirect ro Room page
                        activity.startActivity(new Intent(activity,Room.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 2:
                        //when position 2
                        //redirect ro Table page
                        activity.startActivity(new Intent(activity,Table.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;

                    case 3:
                        //when position 2
                        //redirect ro Table page
                        activity.startActivity(new Intent(activity,Food.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 4:
                        //when position 2
                        //redirect ro Table page
                        activity.startActivity(new Intent(activity,Experience.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 5:
                        //when position 2
                        //redirect ro Table page
                        activity.startActivity(new Intent(activity,Booking.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case 6:
                        //when position 3
                        //initialize alert dialog

                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        //set title
                        builder.setTitle("Exit");
                        //set Message
                        builder.setMessage("Are you sure you want to exist ?");
                        //positive yes button
                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //finish all acitivity
                                activity.finishAffinity();
                                //exit app
                                System.exit(0);
                            }
                        });
                        //negative cancel
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //dismiss dialog
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                        break;

                }
            }


        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //initialize variable
        TextView textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view);
        }
    }
}
