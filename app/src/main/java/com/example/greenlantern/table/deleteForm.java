package com.example.greenlantern.table;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

            showToast();
            Intent intent1 = new Intent(getApplicationContext(),tableBooking.class);
            startActivity(intent1);
        });


    }

    //custom toast message

    public void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_message));
        layout.setBackgroundColor(Color.parseColor("#FF0000"));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText("Reservation cancelled!");

        toastImage.setImageResource(R.drawable.emote);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG*3);


        toast.setView(layout);


        toast.show();
    }
}