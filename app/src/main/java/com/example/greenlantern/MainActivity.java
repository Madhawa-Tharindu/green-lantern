package com.example.greenlantern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    static ArrayList<String> arrayList = new ArrayList<>();
    MainAdapter adapter;

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open
            //close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu =findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //clear array list
        arrayList.clear();

        //add menu item in array list
        arrayList.add("Home");
        arrayList.add("Room");
        arrayList.add("Table");
        arrayList.add("Food");
        arrayList.add("Experience");
        arrayList.add("My Booking");
        arrayList.add("Exit");


        //intialize adapter
        adapter = new MainAdapter(this,arrayList);

        //set Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(adapter);
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }
    @Override
    protected  void onPause(){
        super.onPause();

        //close drawer
        closeDrawer(drawerLayout);

    }


}