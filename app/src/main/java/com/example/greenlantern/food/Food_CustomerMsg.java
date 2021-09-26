package com.example.greenlantern.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Food_CustomerMsg extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    AppCompatButton btn_submit;
    EditText date,name,note;
    String pname,mobile,tablenum,cat;
    int pimageid,pid,qty;
    ProgressBar progress_circular;
    float total;
    DatabaseReference dbRef;
    private StorageReference storageReference;
    public Uri imageurl;
    private FirebaseStorage storage;
    public String ImgUrl,dnow;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_customer_msg);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        date = findViewById(R.id.date);
        name = findViewById(R.id.name);
        note = findViewById(R.id.note);
        btn_submit = findViewById(R.id.btn_submit);
        imageview = findViewById(R.id.imageview);



        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        dbRef = FirebaseDatabase.getInstance().getReference().child("FoodOrders");
        progress_circular = findViewById(R.id.progress_circular);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));

        getDataIntent();
        DateSet();

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  DataAdd();
                ProductAdd();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    private void DateSet(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        dnow = String.valueOf(now);
        date.setText(dnow);
        Log.e("CheckData",dnow);
    }

    private void getDataIntent(){

        Intent i = getIntent();
        pid = i.getIntExtra("ID",0);
        pname = i.getStringExtra("Name");
        total = i.getFloatExtra("total",0f);
        pimageid = i.getIntExtra("image",0);
        qty = i.getIntExtra("qty",0);
        mobile = i.getStringExtra("mobile");
        tablenum = i.getStringExtra("table");
        cat = i.getStringExtra("cat");


        imageurl = Uri.parse(String.valueOf(pimageid));
        imageview.setImageResource(pimageid);

        Log.e("CheckImageURI",""+imageurl);

        Log.e("Check1",""+pid);
        Log.e("Check2",""+pname);
        Log.e("Check3",""+total);//null
        Log.e("Check4",""+pimageid);
        Log.e("Check5",""+qty); //null
        Log.e("Check6",""+mobile);
        Log.e("Check7",""+tablenum);
        Log.e("Check8",""+cat);

    }

    private void DataAdd(){
        progress_circular.setVisibility(View.VISIBLE);
        final String randomkey = UUID.randomUUID().toString();
        StorageReference storageReference = storage.getReference();
        StorageReference dpref = storageReference.child("fooditems/"+ UUID.randomUUID().toString());
        dpref.putFile(imageurl)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        dpref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                ImgUrl = uri.toString();
                                Log.e("checkURI",""+ImgUrl);
                                Toast.makeText(Food_CustomerMsg.this, "File Uploaded", Toast.LENGTH_SHORT).show();
                                ProductAdd();
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Error",""+e.getMessage());
                        Toast.makeText(Food_CustomerMsg.this, "Something Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void ProductAdd(){
        String username = name.getText().toString();
        String Notes = note.getText().toString();


        if(username.isEmpty() || Notes.isEmpty()){
            Toast.makeText(Food_CustomerMsg.this, "Please Enter All fields", Toast.LENGTH_SHORT).show();
        }

        else if(username.length() < 3){
            Toast.makeText(Food_CustomerMsg.this, "Please Enter at least 3 letter name", Toast.LENGTH_SHORT).show();
        }
        else{
            FoodOrder_Model orderModel = new FoodOrder_Model(pid,pname,total,ImgUrl,qty,mobile,tablenum,cat,username,dnow,Notes);
            dbRef.push().setValue(orderModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progress_circular.setVisibility(View.GONE);
                    CompleteAlert();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    faileAlert();
                }
            });
        }
    }

    private void CompleteAlert(){

        SweetAlertDialog pdialog = new SweetAlertDialog(Food_CustomerMsg.this, SweetAlertDialog.SUCCESS_TYPE);
        pdialog.setTitleText("Complete");
        pdialog.setContentText("Item Ordered Successfully");
        pdialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismissWithAnimation();
                Intent i = new Intent(Food_CustomerMsg.this,Food_OrderView.class);
                startActivity(i);
            }
        });
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();
    }

    private void faileAlert(){
        SweetAlertDialog pdialog = new SweetAlertDialog(Food_CustomerMsg.this, SweetAlertDialog.ERROR_TYPE);
        pdialog.setTitleText("Incomplete");
        pdialog.setContentText("Something went Wrong!");
        pdialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                sweetAlertDialog.dismissWithAnimation();
            }
        });
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();
    }
}
// this for test code
//
