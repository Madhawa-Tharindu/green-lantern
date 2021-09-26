package com.example.greenlantern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenlantern.food.FoodShop;
import com.example.greenlantern.food.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Food extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;
    CardView shop1,shop2,shop3,shop4;
    SliderView imageSlider;
    public static String shoptype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        //assign variable
        imageSlider = findViewById(R.id.imageSlider);
        shop1 = findViewById(R.id.shop1);
        shop2 = findViewById(R.id.shop2);
        shop3 = findViewById(R.id.shop3);
        shop4 = findViewById(R.id.shop4);
        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);

        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this,MainActivity.arrayList));
        //imageslider
        //test code//////
        setImage_slider();
        ShopOpen();

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }

    private int images[] = {

            R.drawable.addvertisement,
            R.drawable.biriyani_chicken,
            R.drawable.garlic_rice,
            R.drawable.ad2
    };


    private void setImage_slider(){

        imageSlider.setSliderAdapter(new SliderAdapter(images));
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        imageSlider.startAutoCycle();
    }

    private void ShopOpen(){

        shop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food.this, FoodShop.class);
                startActivity(i);
                shoptype ="rice";
            }
        });

        shop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food.this, FoodShop.class);
                startActivity(i);
                shoptype ="drinks";
            }
        });

        shop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food.this, FoodShop.class);
                startActivity(i);
                shoptype ="soup";
            }
        });

        shop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Food.this, FoodShop.class);
                startActivity(i);
                shoptype ="dessert";
            }
        });
    }


}
// error fixed
