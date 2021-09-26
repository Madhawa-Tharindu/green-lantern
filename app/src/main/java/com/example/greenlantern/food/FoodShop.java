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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greenlantern.Food;
import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

import java.util.ArrayList;

public class FoodShop extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView,shoprecycler;
    Food food;
    String ShopType;
    TextView title;
    AppCompatButton btn_orderView;
    private ShopAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_shop);

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        btn_orderView = findViewById(R.id.btn_orderView);
        shoprecycler = findViewById(R.id.shoprecycler);
        title = findViewById(R.id.title);
        food = new Food();
        ShopType = food.shoptype; // assigning shoptype by home icon clicked
        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        shoprecycler.setHasFixedSize(true);
        shoprecycler.setLayoutManager(new LinearLayoutManager(this));

        //set adapter
        recyclerView.setAdapter(new MainAdapter(this, MainActivity.arrayList));
        CheckShop();

        btn_orderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FoodShop.this,Food_OrderView.class);
                startActivity(i);
            }
        });


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

    private void CheckShop(){

        if(ShopType.equals("rice")){
            title.setText("Fried Rice");
            SetRice();
        }
        else if(ShopType.equals("drinks")){
            title.setText("Drinks");
            SetDrinks();
        }
        else if(ShopType.equals("soup")){
            title.setText("Hot Soups");
            SetSoups();
        }
        else if(ShopType.equals("dessert")){
            title.setText("Desserts");
            SetDessert();
        }


    }

    private ArrayList<ShopItemModel> SetRice() {

        ArrayList<ShopItemModel> shopItemModelArrayList = new ArrayList<>();
        shopItemModelArrayList.add(new ShopItemModel(1, "Biriyani Rice,", 1200.00, R.drawable.biriyani_chicken,"Rice"));
        shopItemModelArrayList.add(new ShopItemModel(2, "Garlic Rice,", 800.00, R.drawable.garlic_rice,"Rice"));
        shopItemModelArrayList.add(new ShopItemModel(3, "SeaFood Rice,", 1000.00, R.drawable.rice,"Rice"));
        shopItemModelArrayList.add(new ShopItemModel(4, "Mix Rice,", 1500.00, R.drawable.mix,"Rice"));

        adapter = new ShopAdapter(this,shopItemModelArrayList);
        shoprecycler.setAdapter(adapter);

        adapter.setOnItemClickListner(new ShopAdapter.onItemClickListner() {
            @Override
            public void onCardClick(int position) {
                ShopItemModel data = shopItemModelArrayList.get(position);
                int id = data.getPid();
                String pprice = String.valueOf(data.getFoodprice());
                Log.e("CheckPrice",""+pprice);
                Intent i = new Intent(FoodShop.this,Food_ProductDiscription.class);
                i.putExtra("ID",id);
                i.putExtra("Name",data.getFoodname());
                i.putExtra("price",pprice);
                i.putExtra("image",data.getImage());
                i.putExtra("cat",data.getCategory());
                startActivity(i);
            }
        });

        return shopItemModelArrayList;

    }

    private ArrayList<ShopItemModel> SetDrinks() {

        ArrayList<ShopItemModel> shopItemModelArrayList = new ArrayList<>();
        shopItemModelArrayList.add(new ShopItemModel(1, "Hot Coffee,", 450.00, R.drawable.drink,"Drinks"));
        shopItemModelArrayList.add(new ShopItemModel(2, "Sprite ,", 300.00, R.drawable.sprite,"Drinks"));
        shopItemModelArrayList.add(new ShopItemModel(3, "Pepsi Cup,", 250.00, R.drawable.pepsi,"Drinks"));
        shopItemModelArrayList.add(new ShopItemModel(4, "Cold Coffee,", 600.00, R.drawable.coldcoffee,"Drinks"));

        adapter = new ShopAdapter(this,shopItemModelArrayList);
        shoprecycler.setAdapter(adapter);

        adapter.setOnItemClickListner(new ShopAdapter.onItemClickListner() {
            @Override
            public void onCardClick(int position) {
                ShopItemModel data = shopItemModelArrayList.get(position);
                int id = data.getPid();
                String pprice = String.valueOf(data.getFoodprice());
                Log.e("CheckPrice",""+pprice);
                Intent i = new Intent(FoodShop.this,Food_ProductDiscription.class);
                i.putExtra("ID",id);
                i.putExtra("Name",data.getFoodname());
                i.putExtra("price",pprice);
                i.putExtra("image",data.getImage());
                i.putExtra("cat",data.getCategory());
                startActivity(i);
            }
        });

        return shopItemModelArrayList;

    }

    private ArrayList<ShopItemModel> SetSoups() {

        ArrayList<ShopItemModel> shopItemModelArrayList = new ArrayList<>();
        shopItemModelArrayList.add(new ShopItemModel(1, "Chicken Soup,", 900.00, R.drawable.soup1,"Soup"));
        shopItemModelArrayList.add(new ShopItemModel(2, "Vegetable Soup,", 600.00, R.drawable.soup25,"Soup"));
        shopItemModelArrayList.add(new ShopItemModel(3, "Beef Soup,", 1100.00, R.drawable.soup3,"Soup"));
        shopItemModelArrayList.add(new ShopItemModel(4, "Mutton Soup,", 1200.00, R.drawable.soup4,"Soup"));

        adapter = new ShopAdapter(this,shopItemModelArrayList);
        shoprecycler.setAdapter(adapter);

        adapter.setOnItemClickListner(new ShopAdapter.onItemClickListner() {
            @Override
            public void onCardClick(int position) {
                ShopItemModel data = shopItemModelArrayList.get(position);
                int id = data.getPid();
                String pprice = String.valueOf(data.getFoodprice());
                Log.e("CheckPrice",""+pprice);
                Intent i = new Intent(FoodShop.this,Food_ProductDiscription.class);
                i.putExtra("ID",id);
                i.putExtra("Name",data.getFoodname());
                i.putExtra("price",pprice);
                i.putExtra("image",data.getImage());
                i.putExtra("cat",data.getCategory());
                startActivity(i);
            }
        });

        return shopItemModelArrayList;

    }

    private ArrayList<ShopItemModel> SetDessert() {

        ArrayList<ShopItemModel> shopItemModelArrayList = new ArrayList<>();
        shopItemModelArrayList.add(new ShopItemModel(1, "Chocolate IceCream,", 1200.00, R.drawable.icecream,"Dessert"));
        shopItemModelArrayList.add(new ShopItemModel(2, "Caramel Pudding,", 1000.00, R.drawable.pudding,"Dessert"));
        shopItemModelArrayList.add(new ShopItemModel(3, "Watalappan,", 800.00, R.drawable.watalappan,"Dessert"));
        shopItemModelArrayList.add(new ShopItemModel(4, "Jelly,", 1200.00, R.drawable.jelly,"Dessert"));

        adapter = new ShopAdapter(this,shopItemModelArrayList);
        shoprecycler.setAdapter(adapter);

        adapter.setOnItemClickListner(new ShopAdapter.onItemClickListner() {
            @Override
            public void onCardClick(int position) {
                ShopItemModel data = shopItemModelArrayList.get(position);
                int id = data.getPid();
                String pprice = String.valueOf(data.getFoodprice());
                Log.e("CheckPrice",""+pprice);
                Intent i = new Intent(FoodShop.this,Food_ProductDiscription.class);
                i.putExtra("ID",id);
                i.putExtra("Name",data.getFoodname());
                i.putExtra("price",pprice);
                i.putExtra("image",data.getImage());
                i.putExtra("cat",data.getCategory());
                startActivity(i);
            }
        });

        return shopItemModelArrayList;

    }
}