package com.jeremyleonardo.ezyfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ReceiptAdapter extends CartAdapter {

    public ReceiptAdapter(Context context, ArrayList<Order> orderList) {
        super(context, orderList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_order_receipt, parent, false);
        return new ViewHolder(v);
    }

}
