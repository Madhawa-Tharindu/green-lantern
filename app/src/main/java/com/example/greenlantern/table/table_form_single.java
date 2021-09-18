package com.example.greenlantern.table;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class table_form_single extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;


    TextView tv_table_id;
    TextView tv_amount_value;
    TextView tv_unit_value;
    EditText et_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_form_single);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this,MainActivity.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        //take a single table
        tv_table_id = findViewById(R.id.tv_table_id);
        tv_amount_value = findViewById(R.id.tv_amount_value);
        et_total = findViewById(R.id.et_total);

        //get data by intent
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);

        if(table_id == R.id.tb001){
            tv_table_id.setText("TB001");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");

        }
        else if(table_id==R.id.tb004){
            tv_table_id.setText("TB004");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");
        }




    }

    public void nextPage(View view){

        Intent intentnext = new Intent(this, table_form_2_single.class);
        intentnext.putExtra("table",view.getId());
        startActivity(intentnext);
    }


    public void remove(View view){
        tv_unit_value = findViewById(R.id.tv_unit_price);
        et_total = findViewById(R.id.et_total);
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        if(table_id==R.id.tb001||table_id==R.id.tb004) {
            tv_amount_value.setText("1");
            tv_unit_value.setText("Rs.1200x1");
            et_total.setText("Rs.1200.00");
        }

    }

    public void add(View view){
        tv_unit_value = findViewById(R.id.tv_unit_price);
        et_total = findViewById(R.id.et_total);
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        if(table_id==R.id.tb001||table_id==R.id.tb004) {
            tv_amount_value.setText("2");
            tv_unit_value.setText("Rs.1200x2");
            et_total.setText("Rs.2400.00");
        }

    }




    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }


}