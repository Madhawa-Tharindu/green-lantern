package com.example.greenlantern.table;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

public class Confirmation extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;

    //confirmation details
    EditText forName,fortotal,fordate,fortable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);



        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));

        Intent intent = getIntent();

        //confirmation Details
        String for_name = intent.getStringExtra("for");
        String phone_num = intent.getStringExtra("phone");
        String total = intent.getStringExtra("total");
        String date = intent.getStringExtra("date");
        String tableId=intent.getStringExtra("tableId");


        //fetch ui details
        forName = findViewById(R.id.forname);
        fordate = findViewById(R.id.date);
        fortable = findViewById(R.id.table);
        fortotal=findViewById(R.id.total);


        //assign reservation details
        forName.setText(for_name);
        fordate.setText(date);
        fortable.setText(tableId);
        fortotal.setText(total);

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

    public void back(View view){
        finish();
    }

    public void myBooking(View view){
        Intent intent1 = new Intent(getApplicationContext(),tableBooking.class);
        startActivity(intent1);
    }

}