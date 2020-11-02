package com.jeremyleonardo.ezyfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceiptAdapter extends OrdersAdapter {

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
