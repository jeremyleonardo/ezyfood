package com.jeremyleonardo.ezyfood;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    Context context;
    ArrayList<Order> orderList;

    public OrdersAdapter(Context context, ArrayList<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_order_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvTitle.setText(order.getName());
        holder.tvPrice.setText(String.valueOf(order.getPrice()));
        holder.tvQuantity.setText(String.valueOf(order.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvQuantity;
        TextView tvTitle;
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }

    }
}
