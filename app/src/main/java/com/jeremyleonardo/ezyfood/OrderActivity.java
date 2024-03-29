package com.jeremyleonardo.ezyfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    TextView tvMenu;
    TextView tvPrice;
    EditText etQuantity;
    ImageView ivMenu;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().setTitle("EzyFood : Order");

        tvMenu = findViewById(R.id.menuName);
        tvPrice = findViewById(R.id.price);
        etQuantity = findViewById(R.id.editTextQuantity);
        ivMenu = findViewById(R.id.ivMenu);

        Intent intent = this.getIntent();

        if(getIntent().getExtras() != null) {
            menu = (Menu) getIntent().getSerializableExtra("menu");
            tvMenu.setText(menu.getName());
            tvPrice.setText(String.valueOf(menu.getPrice()));
            ivMenu.setImageResource(menu.getImage());

        } else {
            finish();
        }
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
        Toast.makeText(this, "Berhasil ditambahkan ke cart", Toast.LENGTH_LONG).show();
        finish();
    }

    public void openCart(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}