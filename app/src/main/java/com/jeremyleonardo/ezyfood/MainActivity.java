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

    public void openView(View view) {
        String viewType = "";
        switch (view.getId()) {
            case (R.id.button1):
                viewType = "drinks";
                break;
            case (R.id.button2):
                viewType = "snacks";
                break;
            case (R.id.button3):
                viewType = "foods";
                break;
            case (R.id.button4):
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