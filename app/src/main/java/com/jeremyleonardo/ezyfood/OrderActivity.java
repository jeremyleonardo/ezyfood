package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    TextView tvMenu;
    TextView tvPrice;
    EditText etQuantity;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setTitle("EzyFood : Order");

        tvMenu = findViewById(R.id.menuName);
        tvPrice = findViewById(R.id.price);
        etQuantity = findViewById(R.id.editTextQuantity);

        Intent intent = this.getIntent();

        if(getIntent().getExtras() != null) {
            menu = (Menu) getIntent().getSerializableExtra("menu");
            tvMenu.setText(menu.getName());
            tvPrice.setText(String.valueOf(menu.getPrice()));
        } else {
            finish();
        }
    }

    public void addToCart(View view){
        int qty = 0;
        do {
            try {
                qty = Integer.parseInt(etQuantity.getText().toString());
            } catch (Exception e) {
                Toast.makeText(this, "Quantity harus diisi", Toast.LENGTH_LONG).show();
                return;
            }
        } while(qty == 0);
        PreferenceHelper.addToCart(this, menu, qty);
        finish();
    }

    public void openCart(View view){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}