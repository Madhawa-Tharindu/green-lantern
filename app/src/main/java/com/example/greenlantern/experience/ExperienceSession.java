package com.example.greenlantern.experience;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

import java.util.Calendar;
import java.util.Date;

public class ExperienceSession extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    Button btn_explore;

    TextView tv_session;
    EditText et_res_name;
    EditText et_res_members;
    EditText et_res_mobile;
    TextView tv_res_sel_date;
    Button btn_res_next;

    String userID;
    String session;
    String session_type;

    DatePickerDialog.OnDateSetListener dateSetListener; // DatePickerDialog.OnDateSetListener dateSetListener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_session);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        btn_explore = findViewById(R.id.btn_experience_explore);

        tv_session = findViewById(R.id.tv_session);
        et_res_name = findViewById(R.id.et_res_name);
        et_res_members = findViewById(R.id.et_res_members);
        et_res_mobile = findViewById(R.id.et_res_mobile);

        btn_res_next = findViewById(R.id.btn_res_next);

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

        session = getIntent().getExtras().getString("session_type");
        session_type = session + " Session";
        tv_session.setText(session_type);

        //Intent intent = getIntent();
        userID = getIntent().getExtras().getString("userID");

        tv_res_sel_date = (TextView) findViewById(R.id.tv_res_sel_date);
        tv_res_sel_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                long current_time = new Date().getTime();
                long max_date = current_time + 1209600000;

                DatePickerDialog dialog = new DatePickerDialog(ExperienceSession.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getDatePicker().setMinDate(current_time);
                dialog.getDatePicker().setMaxDate(max_date);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;

                String date = day + "/" + month + "/" + year;
                tv_res_sel_date.setText(date);
            }
        };

        btn_res_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(et_res_name.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(et_res_members.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Members are required", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(et_res_members.getText().toString()) > 5) {
                    Toast.makeText(getApplicationContext(), "Maximum 5 members", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(et_res_mobile.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Mobile number required", Toast.LENGTH_SHORT).show();
                }
                else if(et_res_mobile.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(tv_res_sel_date.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Date is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    next(view);
                }
            }
        });
    }

    public void next(View view) {
        Intent reserve_intent = new Intent(this, ExperienceSessionReserve.class);
        reserve_intent.putExtra("session", session);
        reserve_intent.putExtra("res_name", et_res_name.getText().toString());
        reserve_intent.putExtra("res_members", et_res_members.getText().toString());
        reserve_intent.putExtra("res_mobile", et_res_mobile.getText().toString());
        reserve_intent.putExtra("res_date", tv_res_sel_date.getText().toString());
        reserve_intent.putExtra("userID", userID);
        startActivity(reserve_intent);
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
