package com.example.greenlantern.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Food_OrderView extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView,order_Recycler;
    DatabaseReference databaseReference;
    ArrayList<FoodOrder_Model> products;
    ArrayList<String> keylist;
    ProgressBar progress_circular;
    FoodOderViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_order_view);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        order_Recycler = findViewById(R.id.order_Recycler);
        progress_circular = findViewById(R.id.progress_circular);
        products = new ArrayList<>();
        keylist = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("FoodOrders");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));

        DataLoad();
        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    private void DataLoad(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    progress_circular.setVisibility(View.VISIBLE);
                    FoodOrder_Model product = data.getValue(FoodOrder_Model.class);
                    products.add(product); // user details added to user list
                    Log.e("namecheck", "" + product.getPname());
                    Log.e("total", "" + product.getTotal());
                    Log.e("checkkey", "" + data.getKey());
                    Log.e("checkkey", "" + data.getRef());
                    keylist.add(data.getKey()); //adding keys to different list - path key
                }

                putDataIntoBottomRecyclerView(Food_OrderView.this, products);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Food_OrderView.this, "Something Error Try again later", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void putDataIntoBottomRecyclerView(Context context, ArrayList<FoodOrder_Model> itemsList) {

        Log.e("Listsize_check", "" + itemsList.size());
        if (itemsList.size() == 0){
            Toast.makeText(context, "No Data to Display", Toast.LENGTH_SHORT).show();
        }
        else {
            progress_circular.setVisibility(View.GONE);
            adapter = new FoodOderViewAdapter(this, itemsList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            order_Recycler.setLayoutManager(layoutManager);
            order_Recycler.setAdapter(adapter);

            adapter.setOnItemClickListner(new FoodOderViewAdapter.onItemClickListner() {
                @Override
                public void onItemDelete(int position) {
                    FoodOrder_Model dta = itemsList.get(position);
                    String key = keylist.get(position);
                    //Log.e("checkpostion",""+dta.getPname());
                    Log.e("CheckKey",""+key);

                    databaseReference = FirebaseDatabase.getInstance().getReference("FoodOrders").child(key);
                    databaseReference.removeValue();
                    DeleteSuccess();
                    adapter.updateData(itemsList);

                }
            });
        }
    }

    private void DeleteSuccess() {
        SweetAlertDialog pdialog = new SweetAlertDialog(Food_OrderView.this, SweetAlertDialog.SUCCESS_TYPE);
        pdialog.setTitleText("Completed");
        pdialog.setContentText("Order Deleted Successfully");
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