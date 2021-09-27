package com.example.greenlantern.experience;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.R;

import java.util.ArrayList;
import java.util.Collections;

public class ExperienceReservationAdapter extends RecyclerView.Adapter<ExperienceReservationAdapter.ExperienceReservationViewHolder> {
    ArrayList<ExperienceDB> experience_reservations_list;
    Context context;

    public ExperienceReservationAdapter(Context context, ArrayList<ExperienceDB> experience_reservations_list) {
        this.context = context;
        this.experience_reservations_list = experience_reservations_list;
    }

    @NonNull
    @Override
    public ExperienceReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Collections.reverse(experience_reservations_list);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_experience_reservation, parent, false);
        return new ExperienceReservationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceReservationViewHolder holder, int position) {
        ExperienceDB current_reservation = experience_reservations_list.get(position);

        holder.tv_res_card_session.setText(current_reservation.getSession_type());
        holder.tv_res_card_name.setText(current_reservation.getName());
        holder.tv_res_card_date.setText(current_reservation.getDate());
        holder.tv_res_card_session_ID.setText(current_reservation.getSessionID());
        holder.tv_res_card_mobile.setText(current_reservation.getMobile());
        holder.tv_res_card_members.setText(String.valueOf(current_reservation.getMembers()));
        holder.tv_res_card_total.setText(current_reservation.getTotal());

        holder.txt_option.setOnClickListener(v ->  {
                PopupMenu popupMenu = new PopupMenu(context, holder.txt_option);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_update:
                            Intent update_intent = new Intent(context, ExperienceUpdateReservation.class);

                            update_intent.putExtra("reservation_key", current_reservation.getKey());
                            update_intent.putExtra("session_type", current_reservation.getSession_type());
                            update_intent.putExtra("name", current_reservation.getName());
                            update_intent.putExtra("date", current_reservation.getDate());
                            update_intent.putExtra("sessionID", current_reservation.getSessionID());
                            update_intent.putExtra("mobile", current_reservation.getMobile());
                            update_intent.putExtra("members", String.valueOf(current_reservation.getMembers()));
                            update_intent.putExtra("total", current_reservation.getTotal());



                            context.startActivity(update_intent);
                            break;

                        case R.id.menu_delete:
                            Intent delete_intent = new Intent(context, ExperienceRemoveReservation.class);

                            delete_intent.putExtra("reservation_key", String.valueOf(current_reservation.getKey()));
                            delete_intent.putExtra("session_type", current_reservation.getSession_type());
                            delete_intent.putExtra("date", current_reservation.getDate());
                            delete_intent.putExtra("sessionID", current_reservation.getSessionID());

                            context.startActivity(delete_intent);
                            break;
                    }
                    return false;
                });
                popupMenu.show();

        });
    }

    @Override
    public int getItemCount() {
        return experience_reservations_list.size();
    }

    public static class ExperienceReservationViewHolder extends RecyclerView.ViewHolder {
        TextView tv_res_card_session;
        TextView tv_res_card_name;
        TextView tv_res_card_date;
        TextView tv_res_card_session_ID;
        TextView tv_res_card_mobile;
        TextView tv_res_card_members;
        TextView tv_res_card_total;
        TextView txt_option;

        public ExperienceReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_res_card_session = itemView.findViewById(R.id.tv_res_card_session);
            tv_res_card_name = itemView.findViewById(R.id.tv_res_card_name);
            tv_res_card_date = itemView.findViewById(R.id.tv_res_card_date);
            tv_res_card_session_ID = itemView.findViewById(R.id.tv_res_card_session_id);
            tv_res_card_mobile = itemView.findViewById(R.id.tv_res_card_mobile);
            tv_res_card_members = itemView.findViewById(R.id.tv_res_card_members);
            tv_res_card_total = itemView.findViewById(R.id.tv_res_card_total);
            txt_option = itemView.findViewById(R.id.txt_option);
        }
    }
}
