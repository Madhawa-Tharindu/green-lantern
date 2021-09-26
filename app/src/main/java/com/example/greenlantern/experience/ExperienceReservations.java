package com.example.greenlantern.experience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExperienceReservations extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    RecyclerView res_recycleView;
    ExperienceReservationAdapter res_adapter;
    //private RecyclerView.LayoutManager res_LayoutManager;

    DatabaseReference dbRef;

    ArrayList<ExperienceDB> exp_res_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_reservations);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        res_recycleView = findViewById(R.id.reservations_list);

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

        dbRef = FirebaseDatabase.getInstance().getReference("ExperienceDB");

        res_recycleView.setHasFixedSize(true);
        res_recycleView.setLayoutManager(new LinearLayoutManager(this));

        exp_res_list = new ArrayList<>();

        res_adapter = new ExperienceReservationAdapter(this, exp_res_list);
        res_recycleView.setAdapter(res_adapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ExperienceDB experienceDB = dataSnapshot.getValue(ExperienceDB.class);
                    if(experienceDB.getUserID().equals("GL0001")) {
                        experienceDB.setKey(dataSnapshot.getKey());
                        exp_res_list.add(experienceDB);
                        Log.i("key", experienceDB.getKey());
                    }
                }
                res_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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