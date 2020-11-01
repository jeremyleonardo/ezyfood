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
    }

    public void openCatalog(View view) {
        String catalogType = "";
        switch (view.getId()) {
            case (R.id.button1):
                catalogType = "drinks";
                break;
            case (R.id.button2):
                catalogType = "snacks";
                break;
            case (R.id.button3):
                catalogType = "foods";
                break;
            case (R.id.button4):
                catalogType = "topup";
                break;
        }
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra("catalogType", catalogType);
        startActivity(intent);
    }

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}