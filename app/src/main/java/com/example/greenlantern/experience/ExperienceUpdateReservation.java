package com.example.greenlantern.experience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class ExperienceUpdateReservation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;

    DatabaseReference dbRef;

    TextView tv_res_update_date, tv_res_update_sessionID, tv_res_update_total;
    EditText et_res_update_name, et_res_update_members, et_res_update_mobile;
    Button btn_res_update, btn_back;

    String new_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_update_reservation);

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

        Intent intent = getIntent();
        String update_key = intent.getStringExtra("reservation_key");
        String session_type = intent.getStringExtra("session_type");
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String session_id = intent.getStringExtra("sessionID");
        String mobile = intent.getStringExtra("mobile");
        String members = intent.getStringExtra("members");
        String total = intent.getStringExtra("total");
        String userID = intent.getStringExtra("userID");

        //Log.i("Members", members);

        tv_res_update_date = findViewById(R.id.tv_res_update_date);
        tv_res_update_sessionID = findViewById(R.id.tv_res_update_session_id);
        et_res_update_name = findViewById(R.id.et_res_update_name);
        et_res_update_members = findViewById(R.id.et_res_update_members);
        et_res_update_mobile = findViewById(R.id.et_res_update_mobile);
        tv_res_update_total = findViewById(R.id.tv_res_update_total);
        btn_res_update = findViewById(R.id.btn_res_update);
        btn_back = findViewById(R.id.btn_back);

        tv_res_update_date.setText(date);
        tv_res_update_sessionID.setText(session_id);
        et_res_update_name.setText(name);
        et_res_update_members.setText(String.valueOf(members));
        et_res_update_mobile.setText(mobile);

        tv_res_update_total.setText(total);

        dbRef = FirebaseDatabase.getInstance().getReference("ExperienceDB");

        btn_res_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(et_res_update_name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(et_res_update_members.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Member(s) is required", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(et_res_update_members.getText().toString()) > 5) {
                    Toast.makeText(getApplicationContext(), "Maximum 5 members", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(et_res_update_mobile.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Mobile is required", Toast.LENGTH_SHORT).show();
                }
                else if(et_res_update_mobile.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Invalid mobile", Toast.LENGTH_SHORT).show();
                } else {
                    if(session_type.equals("Tennis")) {
                        new_total = "Rs. " + String.valueOf(Integer.parseInt(et_res_update_members.getText().toString()) * 1000) + ".00";
                    } else if(session_type.equals("Swimming")) {
                        new_total = "Rs. " + String.valueOf(Integer.parseInt(et_res_update_members.getText().toString()) * 1200) + ".00";
                    } else if(session_type.equals("Gym")) {
                        new_total = "Rs. " + String.valueOf(Integer.parseInt(et_res_update_members.getText().toString()) * 1500) + ".00";
                    }

                    dbRef.child(update_key).child("name").setValue(et_res_update_name.getText().toString());
                    dbRef.child(update_key).child("members").setValue(Integer.parseInt(et_res_update_members.getText().toString()));
                    dbRef.child(update_key).child("mobile").setValue(et_res_update_mobile.getText().toString());
                    dbRef.child(update_key).child("total").setValue(new_total);

                    Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(view -> {
            Intent back_intent = new Intent(ExperienceUpdateReservation.this, ExperienceReservations.class);
            back_intent.putExtra("userID", userID);
            startActivity(back_intent);
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}
