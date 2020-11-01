package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private String catalogType;
    private TextView tvCatalogType;

    List<Menu> menuList = new ArrayList<>();
    MenusAdapter adapter;
    RecyclerView rvMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        tvCatalogType = findViewById(R.id.textView);

        Intent intent = getIntent();
        catalogType = intent.getStringExtra("catalogType");

        tvCatalogType.setText(catalogType.substring(0, 1).toUpperCase() + catalogType.substring(1));

        if (catalogType.equals("drinks")){
            menuList.add(new Menu("Air Mineral", 4000));
            menuList.add(new Menu("Jus Apel", 8000));
            menuList.add(new Menu("Jus Mangga", 9000));
            menuList.add(new Menu("Jus Alpukat", 10000));
        } else if (catalogType.equals("snacks")){
            menuList.add(new Menu("Kukis", 4000));
            menuList.add(new Menu("Kue", 6000));
            menuList.add(new Menu("Keripik", 4000));
            menuList.add(new Menu("Roti", 8000));
        } else if (catalogType.equals("foods")){
            menuList.add(new Menu("Bakmi", 20000));
            menuList.add(new Menu("Bihun", 18000));
            menuList.add(new Menu("Kwetiau", 19000));
            menuList.add(new Menu("Nasi Goreng", 22000));
        }


        rvMenus = findViewById(R.id.rvMenus);
        rvMenus.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MenusAdapter(this, (ArrayList<Menu>) menuList);
        rvMenus.setAdapter(adapter);

    }

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}