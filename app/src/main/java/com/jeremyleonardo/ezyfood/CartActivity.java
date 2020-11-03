package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<Order> orderList;
    CartAdapter adapter;
    RecyclerView rvOrders;

    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        populateOrderList();
        attachTotalPrice();
        fitAdapter();
        attachBalance();

        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        populateOrderList();
        attachTotalPrice();
        fitAdapter();
        attachBalance();
    }

    private void populateOrderList(){
        orderList = PreferenceHelper.getAllFromCart(this);
    }

    private void attachTotalPrice(){
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);
        totalPrice = PreferenceHelper.getTotalPrice(this);
        tvTotalPrice.setText("Total Price : Rp " + totalPrice);
    }

    private void attachBalance(){
        TextView tvBalance = findViewById(R.id.tvBalance);
        tvBalance.setText("Wallet Balance : Rp " + PreferenceHelper.getWalletBalance(this));
    }

    private void fitAdapter(){
        rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, (ArrayList<Order>) orderList);
        rvOrders.setAdapter(adapter);
    }

    public void pay(View view){
        if(PreferenceHelper.getWalletBalance(this) >= totalPrice && totalPrice > 0){
            Intent intent = new Intent(this, ReceiptActivity.class);
            startActivity(intent);
        } else {
            if(PreferenceHelper.getWalletBalance(this) < totalPrice){
                Toast.makeText(this, "Saldo wallet harus lebih dari total harga", Toast.LENGTH_LONG).show();
            }else if (totalPrice <= 0) {
                Toast.makeText(this, "Harus ada barang yang diorder", Toast.LENGTH_LONG).show();
            }
        }
    }

    SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (key.equals("cart")){
                populateOrderList();
                attachTotalPrice();
                fitAdapter();
            }
        }
    };

}