package com.example.greenlantern.experience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenlantern.Booking;
import com.example.greenlantern.Experience;
import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ExperienceSessionReserve extends AppCompatActivity {
    TextView tv_res_confirm_session;
    TextView tv_res_confirm_name;
    TextView tv_res_confirm_date;
    TextView tv_res_confirm_session_id;
    TextView tv_res_confirm_members;
    TextView tv_res_confirm_mobile;
    TextView tv_res_total;
    Button btn_res_confirm;

    String session, session_name;
    String reserve_name, res_name_view;
    String reserve_date, res_date_view;
    String reserve_session_id, res_session_id_view;
    String reserve_mobile, res_mobile_view;
    String reserve_members, res_members_view;
    String reserve_total, res_total_view;
    String userID;

    Random random = new Random();

    DatabaseReference dbRef;
    ExperienceDB exp;

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_session_reserve);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        exp = new ExperienceDB();

        tv_res_confirm_session = findViewById(R.id.tv_res_confirm_session);
        tv_res_confirm_name = findViewById(R.id.tv_res_confirm_name);
        tv_res_confirm_date = findViewById(R.id.tv_res_confirm_date);
        tv_res_confirm_session_id = findViewById(R.id.tv_res_confirm_session_id);
        tv_res_confirm_members = findViewById(R.id.tv_res_confirm_members);
        tv_res_confirm_mobile = findViewById(R.id.tv_res_confirm_mobile);
        tv_res_total = findViewById(R.id.tv_res_total);
        btn_res_confirm = findViewById(R.id.btn_res_confirm);

        session = getIntent().getExtras().getString("session");
        reserve_name = getIntent().getExtras().getString("res_name");
        reserve_date = getIntent().getExtras().getString("res_date");
        reserve_mobile = getIntent().getExtras().getString("res_mobile");
        reserve_members = getIntent().getExtras().getString("res_members");
        userID = getIntent().getExtras().getString("userID");


        if(session.equals("Tennis")) {
            reserve_total = "Rs. " + String.valueOf(Integer.parseInt(reserve_members) * 1000) + ".00";
        } else if(session.equals("Swimming")) {
            reserve_total = "Rs. " + String.valueOf(Integer.parseInt(reserve_members) * 1200) + ".00";
        } else if(session.equals("Gym")) {
            reserve_total = "Rs. " + String.valueOf(Integer.parseInt(reserve_members) * 1500) + ".00";
        }

        session_name = session + " Session";
        tv_res_confirm_session.setText(session_name);

        res_date_view = "Session Date - " + reserve_date;
        tv_res_confirm_date.setText(res_date_view);

        res_name_view = "Name - " + reserve_name;
        tv_res_confirm_name.setText(res_name_view);

        res_mobile_view = "Mobile - " + reserve_mobile;
        tv_res_confirm_mobile.setText(res_mobile_view);

        res_members_view = "Members - " + reserve_members;
        tv_res_confirm_members.setText(res_members_view);

        if(session.equals("Swimming")) {
            reserve_session_id = "SWM" + userID + String.valueOf(random.nextInt(100));
            res_session_id_view = "Session ID - " + reserve_session_id;
        }
        else if(session.equals("Gym")) {
            reserve_session_id = "GYM" + userID + String.valueOf(random.nextInt(100));
            res_session_id_view = "Session ID - " + reserve_session_id;
        }
        else if(session.equals("Tennis")) {
            reserve_session_id = "TEN" + userID + String.valueOf(random.nextInt(100));
            res_session_id_view = "Session ID - " + reserve_session_id;
        }

        tv_res_confirm_session_id.setText(res_session_id_view);

        res_total_view = "Total - " + reserve_total;
        tv_res_total.setText(res_total_view);

        btn_res_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("ExperienceDB");
                exp = new ExperienceDB(session, reserve_session_id, userID, reserve_date, reserve_name, reserve_mobile, Integer.parseInt(reserve_members), reserve_total);
                dbRef.push().setValue(exp);

                Toast.makeText(getApplicationContext(), "Session reserved", Toast.LENGTH_SHORT).show();

                Intent experience_reservation_intent = new Intent(ExperienceSessionReserve.this, ExperienceReservations.class);
                experience_reservation_intent.putExtra("userID", userID);
                startActivity(experience_reservation_intent);
            }
        });
    }

    public void back(View view){
        finish();
    }


    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}