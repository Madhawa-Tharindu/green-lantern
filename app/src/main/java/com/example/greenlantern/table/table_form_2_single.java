package com.example.greenlantern.table;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.example.greenlantern.Table;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class table_form_2_single extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    Button reserve;
    TextView tv_validation;
    //get Date
    CalendarView calendarView;
    String curYear;
    String curDate;
    String curMonth;
    String date;
    String userId;



    //database ref for date validation
    DatabaseReference db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_form2_single);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        reserve = findViewById(R.id.btn_reserve);
        tv_validation =findViewById(R.id.tv_validation);
        final Calendar calendar = Calendar.getInstance();

        //database referrence
        DAOETable dao=new DAOETable();



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
        String table = String.valueOf(table_id);
        String for_name = intent.getStringExtra("for");
        String phone_num = intent.getStringExtra("phone");
        String total = intent.getStringExtra("total");
        String amount = intent.getStringExtra("amount");
        String tableId=intent.getStringExtra("tableId");
        userId =intent.getStringExtra("userId");

        //fetch data for the date validation
        db = FirebaseDatabase.getInstance().getReference("TableD");



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

                date = curMonth+"-"+curDate+"-"+curYear;
                Log.i("date",date);
                Log.i("table",tableId);


                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                            TableD table =dataSnapshot.getValue(TableD.class);
                            if(table.getTableNo().equals(tableId) && table.getReservedDate().equals(date)) {
                                Log.i("exist","date exist");
                                tv_validation.setText("Table already Reserved ! Don't Worry ! It's Time To Change Your Table or Date! ");
                                reserve.setVisibility(View.INVISIBLE);

                            }else {
                                tv_validation.setText("Day is Yours ! Hurry up ! Reserve Your Table");
                                reserve.setVisibility(View.VISIBLE);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





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

                Log.i("table",table);

                if(date==null){
                    tv_validation.setText("It's Time to Select a Date !");
                }else {
                    //insert into database
                    TableD table = new TableD(userId, tableId, for_name, phone_num, total, amount, date);
                    dao.insert(table).addOnSuccessListener(suc -> {
                        showToast();

                        //redirect to confirmation page
                        Intent intent1 = new Intent(getApplicationContext(),Confirmation.class);

                        intent1.putExtra("tableId",tableId.toString());
                        intent1.putExtra("for",for_name.toString());
                        intent1.putExtra("phone",phone_num.toString());
                        intent1.putExtra("total",total.toString());
                        intent1.putExtra("date",date.toString());


                        getApplicationContext().startActivity(intent1);

                    }).addOnFailureListener(er -> {
                        Toast.makeText(getApplicationContext(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                    });

                }
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

    //custom toast message

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_message));


        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText("Table Reserved On "+date);

        toastImage.setImageResource(R.drawable.emote);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG*5);


        toast.setView(layout);


        toast.show();
    }


}