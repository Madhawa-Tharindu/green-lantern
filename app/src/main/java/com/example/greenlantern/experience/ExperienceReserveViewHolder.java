package com.example.greenlantern.experience;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.R;

public class ExperienceReserveViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_option;

    public ExperienceReserveViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
