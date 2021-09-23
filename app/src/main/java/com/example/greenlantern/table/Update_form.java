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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_form extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;

    TextView tableId,tableName,tableDate,tablePhone,tableTotal;
    EditText updateName,updatePhoneNum;
    Button btn_update;

    //database ref for data update
    DatabaseReference db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_form);

        //show update values
        Intent intent = getIntent();
        String key = intent.getStringExtra("editKey");
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String table = intent.getStringExtra("table");
        String total = intent.getStringExtra("total");
        String phone = intent.getStringExtra("phone");

        //fetch data for the update
        db = FirebaseDatabase.getInstance().getReference("TableD");


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

        //get update details
        updateName = findViewById(R.id.etUpdateName);
        updatePhoneNum =findViewById(R.id.etUpdateNumber);
        btn_update =findViewById(R.id.btn_update);

        //set values
        tableId.setText(table);
        tableName.setText(name);
        tableTotal.setText(total);
        tableDate.setText(date);
        tablePhone.setText(phone);
        updateName.setText(name);
        updatePhoneNum.setText(phone);


        //update data function
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("name",updateName.toString());
                Log.i("name",updatePhoneNum.toString());

                //update database

                db.child(key).child("forName").setValue(updateName.getText().toString());
                db.child(key).child("phoneNum").setValue(updatePhoneNum.getText().toString());
                Toast.makeText(getApplicationContext(),"Details Update Successfully",Toast.LENGTH_SHORT).show();

            }
        });





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