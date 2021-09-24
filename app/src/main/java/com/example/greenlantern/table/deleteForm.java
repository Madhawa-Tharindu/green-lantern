package com.example.greenlantern.table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.greenlantern.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deleteForm extends AppCompatActivity {

    Button btn_yes,btn_no;
    //database ref for data delete
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_form);

        Intent intent = getIntent();

        btn_no = findViewById(R.id.btn_no);
        btn_yes = findViewById(R.id.btn_cancel);

        btn_no.setOnClickListener(view -> {
            finish();
        });

        //fetch data for the delete function
        db = FirebaseDatabase.getInstance().getReference("TableD");

        //delete Reservation

        btn_yes.setOnClickListener(view -> {


            String key=intent.getStringExtra("deleteKey");
            db.child(key).removeValue();

            Intent intent1 = new Intent(getApplicationContext(),tableBooking.class);
            this.startActivity(intent1);
        });


    }
}