package com.jeremyleonardo.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("EzyFood");
        getSupportActionBar().setSubtitle("2201731106 - Jeremy Leonardo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    public void openView(View view) {
        String viewType = "";
        switch (view.getId()) {
            case (R.id.llDrinks):
                viewType = "drinks";
                break;
            case (R.id.llSnacks):
                viewType = "snacks";
                break;
            case (R.id.llFoods):
                viewType = "foods";
                break;
            case (R.id.llTopUp):
                viewType = "topup";
                break;
        }
        if (viewType.equals("topup")){
            Intent intent = new Intent(this, TopUpActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, CatalogActivity.class);
            intent.putExtra("catalogType", viewType);
            startActivity(intent);
        }
    }

    public void openCart(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}