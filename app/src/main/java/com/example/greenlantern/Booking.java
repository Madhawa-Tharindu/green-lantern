package com.example.greenlantern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenlantern.food.Food_OrderView;

public class Booking extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    AppCompatButton btn_foodorder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        btn_foodorder = findViewById(R.id.btn_foodorder);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this,MainActivity.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btn_foodorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Booking.this, Food_OrderView.class);
                startActivity(i);
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