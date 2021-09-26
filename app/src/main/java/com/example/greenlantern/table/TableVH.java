package com.example.greenlantern.table;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.R;

public class TableVH extends RecyclerView.ViewHolder {

    public TextView txt_option;

    public TableVH(@NonNull View itemView) {
        super(itemView);
        txt_option = itemView.findViewById(R.id.txt_option);

    }


}
