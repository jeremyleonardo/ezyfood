package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("EzyFood");
        getSupportActionBar().setSubtitle("2201731106 - Jeremy Leonardo");
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

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}