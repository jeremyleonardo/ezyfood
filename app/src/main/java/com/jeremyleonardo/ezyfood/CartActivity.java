package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayList<Order> orderList;
    OrdersAdapter adapter;
    RecyclerView rvOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        populateOrderList();
        attachTotalPrice();
        fitAdapter();

        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.preference_file_key), MODE_PRIVATE);
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferenceChangeListener);

    }

    private void populateOrderList(){
        orderList = PreferenceHelper.getAllFromCart(this);

    }

    private void attachTotalPrice(){
        TextView tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("Total Price : Rp " + PreferenceHelper.getTotalPrice(this));
    }

    private void fitAdapter(){
        rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrdersAdapter(this, (ArrayList<Order>) orderList);
        rvOrders.setAdapter(adapter);
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