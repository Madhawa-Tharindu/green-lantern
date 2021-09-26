package com.example.greenlantern.table;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenlantern.Booking;
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
    Button btn_update,btn_back;

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
        btn_back=findViewById(R.id.btn_back_booking);


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
                Log.i("name",updateName.getText().toString());
                Log.i("name",updatePhoneNum.getText().toString());


                if(TextUtils.isEmpty(updateName.getText().toString())){
                    Toast.makeText(getApplicationContext(),"For Can not be empty!",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(updatePhoneNum.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Phone Number Can not be empty!",Toast.LENGTH_SHORT).show();
                }
                else if(updateName.getText().toString().matches(".*\\d.*")){
                    Toast.makeText(getApplicationContext(),"Name Can not be A number !",Toast.LENGTH_SHORT).show();
                }

                else if(updateName.getText().toString().length()>20){
                    Toast.makeText(getApplicationContext(),"Name Must be 1 to 20 Character !",Toast.LENGTH_SHORT).show();
                }
                else if(updatePhoneNum.getText().toString().length()!=10){
                    Toast.makeText(getApplicationContext(),"Phone Number Must be 10 Numbers !",Toast.LENGTH_SHORT).show();
                }else {
                    //update database

                    db.child(key).child("forName").setValue(updateName.getText().toString());
                    db.child(key).child("phoneNum").setValue(updatePhoneNum.getText().toString());
                    //Toast.makeText(getApplicationContext(), "Details Update Successfully", Toast.LENGTH_SHORT).show();
                    showToast();

                }
            }
        });


        btn_back.setOnClickListener(view -> {
           Log.i("btn","clicked");
           Intent intents = new Intent(this, tableBooking.class);
           startActivity(intents);
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

    //custom toast message

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_message));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText("Details Updated Successfully!");
        toastImage.setImageResource(R.drawable.emote);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG*3);


        toast.setView(layout);


        toast.show();
    }



    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }



}