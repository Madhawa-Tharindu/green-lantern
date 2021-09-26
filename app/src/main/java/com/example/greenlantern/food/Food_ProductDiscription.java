package com.example.greenlantern.food;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

public class Food_ProductDiscription extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView btMenu;
    AppCompatButton btn_next;
    RecyclerView recyclerView;
    ImageView image;
    TextView pname,price;
    TextView txtItemQty;
    EditText tableno,phone,tprice;
    ImageButton imgBtnQtyMinus,imgBtnQtyPlus;
    int pid;
    private int userOrderdQty=0;
    int pimage,numprice;
    String proname,cat;
    String pprice;
    float total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_product_discription);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        image = findViewById(R.id.image);
        pname = findViewById(R.id.pname);
        pname = findViewById(R.id.pname);
        price = findViewById(R.id.price);
        tableno = findViewById(R.id.tableno);
        phone = findViewById(R.id.phone);
        tprice = findViewById(R.id.tprice);
        txtItemQty = findViewById(R.id.txtItemQty);
        imgBtnQtyMinus = findViewById(R.id.imgBtnQtyMinus);
        imgBtnQtyPlus = findViewById(R.id.imgBtnQtyPlus);
        btn_next = findViewById(R.id.btn_next);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDataIntent();
        QtyChange();

        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        Next();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    private void getDataIntent(){

        Intent i = getIntent();
        pid = i.getIntExtra("ID",0);
        proname = i.getStringExtra("Name");
        pimage = i.getIntExtra("image",0);
        pprice = i.getStringExtra("price");

        try {
            numprice = Integer.parseInt(pprice);

        }
        catch (Exception e){

        }
        Log.e("CheckIntPrice",""+numprice);
        cat = i.getStringExtra("cat");
        Log.e("Checkprice",""+pprice);
        pname.setText(proname);
        price.setText("LKR. "+pprice);
        Glide.with(this)
                .load(pimage)
                .fitCenter()
                .into(image);

    }

    private void QtyChange() {
        tprice.setText("LKR " +pprice);
        imgBtnQtyPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++userOrderdQty;
                txtItemQty.setText(String.valueOf(userOrderdQty));
                Log.e("Clicked +", "");
                float pprice1 = Float.parseFloat(pprice);
                total = pprice1 * userOrderdQty;
                tprice.setText("LKR " + total);
                Log.e("Check Totalprice", "" + total);
            }
        });

        imgBtnQtyMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userOrderdQty > 0) {
                    --userOrderdQty;
                    txtItemQty.setText(String.valueOf(userOrderdQty));
                    Log.e("Clicked -", "");
                    float pprice1 = Float.parseFloat(pprice);
                    total = pprice1 * userOrderdQty;
                    tprice.setText("LKR " + total);
                    Log.e("Check Totalprice", "" + total);
                }
            }
        });

    }
    private void Next(){

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mobile = phone.getText().toString();
                String tablenum = tableno.getText().toString();
                Log.e("CheckPID",""+pid);
                Log.e("CheckPID",""+total);
                Log.e("CheckPID",""+userOrderdQty);
                if (mobile.isEmpty() || tablenum.isEmpty()) {
                    Toast.makeText(Food_ProductDiscription.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if(userOrderdQty == 0){
                    Toast.makeText(Food_ProductDiscription.this, "Please add quantity and continue", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent i = new Intent(Food_ProductDiscription.this, Food_CustomerMsg.class);
                    i.putExtra("ID", pid);
                    i.putExtra("Name", proname);
                    i.putExtra("total", total);
                    i.putExtra("image", pimage);
                    i.putExtra("qty", userOrderdQty);
                    i.putExtra("mobile", mobile);
                    i.putExtra("table", tablenum);
                    i.putExtra("cat", cat);
                    startActivity(i);
                }
            }
        });
    }
}