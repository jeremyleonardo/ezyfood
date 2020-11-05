package com.jeremyleonardo.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CatalogActivity extends AppCompatActivity {

    private String catalogType;
    private TextView tvCatalogType;

    List<Menu> menuList = new ArrayList<>();
    CatalogAdapter adapter;
    RecyclerView rvMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        tvCatalogType = findViewById(R.id.textView);

        Intent intent = getIntent();
        catalogType = intent.getStringExtra("catalogType");
        getSupportActionBar().setTitle("EzyFood : " + catalogType.substring(0, 1).toUpperCase() + catalogType.substring(1));

        tvCatalogType.setText(catalogType.substring(0, 1).toUpperCase() + catalogType.substring(1));

        if (catalogType.equals("drinks")){
            menuList.add(new Menu("Air Mineral", "drink", 4000));
            menuList.add(new Menu("Jus Apel", "drink", 8000));
            menuList.add(new Menu("Jus Mangga", "drink", 9000));
            menuList.add(new Menu("Jus Alpukat", "drink", 10000));
        } else if (catalogType.equals("snacks")){
            menuList.add(new Menu("Kukis", "snack", 4000));
            menuList.add(new Menu("Kue", "snack", 6000));
            menuList.add(new Menu("Keripik", "snack", 4000));
            menuList.add(new Menu("Roti", "snack", 8000));
        } else if (catalogType.equals("foods")){
            menuList.add(new Menu("Bakmi", "food", 20000));
            menuList.add(new Menu("Bihun", "food", 18000));
            menuList.add(new Menu("Kwetiau", "food", 19000));
            menuList.add(new Menu("Nasi Goreng", "food", 22000));
        }


        rvMenus = findViewById(R.id.rvMenus);
        rvMenus.setLayoutManager(new GridLayoutManager(this,2));

        adapter = new CatalogAdapter(this, (ArrayList<Menu>) menuList);
        rvMenus.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.default_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cartMenu){
            openCart();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openCart(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}