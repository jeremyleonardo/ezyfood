package com.jeremyleonardo.ezyfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ReceiptActivity extends AppCompatActivity {

    ArrayList<Order> orderList;
    CartAdapter adapter;
    RecyclerView rvOrders;
    TextView tvTotalPrice;

    String totalPriceStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        if(savedInstanceState != null) {
            orderList = savedInstanceState.getParcelableArrayList("orders");
            totalPriceStr = (String) savedInstanceState.getString("priceText");
            tvTotalPrice.setText(totalPriceStr);
        } else {
            attachTotalPrice();
            populateOrderList();
        }
        fitAdapter();
        if (savedInstanceState == null) calculate();

    }

    private void populateOrderList(){
        orderList = PreferenceHelper.getAllFromCart(this);
    }

    private void attachTotalPrice(){
        int totalPrice = PreferenceHelper.getTotalPrice(this);
        tvTotalPrice.setText("Total Price : Rp " + totalPrice);
    }

    private void fitAdapter(){
        rvOrders = findViewById(R.id.rvOrders);
        rvOrders.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReceiptAdapter(this, (ArrayList<Order>) orderList);
        rvOrders.setAdapter(adapter);
    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void calculate(){
        int totalPrice = PreferenceHelper.getTotalPrice(this);
        PreferenceHelper.reduceBalance(this, totalPrice);
        PreferenceHelper.emptyCart(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String priceText = tvTotalPrice.getText().toString();
        bundle.putParcelableArrayList("orders", orderList);
        bundle.putString("priceText", priceText);
    }

}