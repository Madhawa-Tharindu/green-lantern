package com.example.greenlantern.experience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

public class ExperienceSelectExp extends AppCompatActivity {
    Button btn_pool_select;
    Button btn_gym_select;
    Button btn_tennis_select;

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    Button btn_explore;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_select_exp);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        btn_explore = findViewById(R.id.btn_experience_explore);

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

        btn_pool_select = findViewById(R.id.btn_pool_select);
        btn_gym_select = findViewById(R.id.btn_gym_select);
        btn_tennis_select = findViewById(R.id.btn_tennis_select);

        userID = getIntent().getExtras().getString("userID");

        btn_pool_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_pool_select = new Intent(ExperienceSelectExp.this, ExperienceSession.class);
                intent_pool_select.putExtra("session_type", "Swimming");
                intent_pool_select.putExtra("userID", userID);
                startActivity(intent_pool_select);
            }
        });

        btn_gym_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_gym_select = new Intent(ExperienceSelectExp.this, ExperienceSession.class);
                intent_gym_select.putExtra("session_type", "Gym");
                intent_gym_select.putExtra("userID", userID);
                startActivity(intent_gym_select);
            }
        });

        btn_tennis_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_tennis_select = new Intent(ExperienceSelectExp.this, ExperienceSession.class);
                intent_tennis_select.putExtra("session_type", "Tennis");
                intent_tennis_select.putExtra("userID", userID);
                startActivity(intent_tennis_select);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}