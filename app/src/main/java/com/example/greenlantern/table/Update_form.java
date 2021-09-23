package com.example.greenlantern.table;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

public class Update_form extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;

    TextView tableId,tableName,tableDate,tablePhone,tableTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);

        //get update key
        Intent intent = getIntent();
        String key = intent.getStringExtra("editKey");
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String table = intent.getStringExtra("table");
        String total = intent.getStringExtra("total");
        String phone = intent.getStringExtra("phone");

        Log.i("Update key",key);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //assign update text views
        tableId=findViewById(R.id.tableId);
        tableName=findViewById(R.id.tablename);
        tableDate=findViewById(R.id.tabledate);
        tablePhone=findViewById(R.id.tablephone);
        tableTotal=findViewById(R.id.tabletotal);

        //set values
        tableId.setText(table);
        tableName.setText(name);
        tableTotal.setText(total);
        tableDate.setText(date);
        tablePhone.setText(phone);





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



    }

    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }



}