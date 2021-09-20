package com.example.greenlantern.table;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

import java.util.Calendar;

public class table_form_2_single extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    Button reserve;

    //get Date
    CalendarView calendarView;
    String curYear;
    String curDate;
    String curMonth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_form2_single);


        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        reserve = findViewById(R.id.btn_reserve);
        final Calendar calendar = Calendar.getInstance();

        //pickup date
        calendarView = findViewById(R.id.calendarView);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this,MainActivity.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        String for_name = intent.getStringExtra("for");
        String phone_num = intent.getStringExtra("phone");
        String total = intent.getStringExtra("total");
        String amount = intent.getStringExtra("amount");


        //pickup date
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                //disable previous view
                //calendarView.setMinDate(calendar.getTimeInMillis());

                curMonth= String.valueOf(month);
                curYear = String.valueOf(year);
                curDate = String.valueOf(dayOfMonth);

            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //log messages
                Log.i("name",for_name);
                Log.i("name",phone_num);
                Log.i("name",amount);
                Log.i("name",total);
                Log.i("date",curDate);
                Log.i("date",curYear);
                Log.i("date",curMonth);


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