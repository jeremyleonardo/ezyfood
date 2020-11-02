package com.jeremyleonardo.ezyfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

public class PreferenceHelper {

    public static void addToCart(Context context, Menu menu, int qty){
        String cartString = getAllFromCartStringFormat(context);
        if(cartString.contains(menu.toString())){
            ArrayList<Order> orderList = getAllFromCart(context);
            emptyCart(context);
            for (Order order: orderList) {
                if(order.getName().equals(menu.getName())){
                    order.setQuantity(order.getQuantity() + qty);
                }
                addToCart(context, order.toMenu(), order.getQuantity());
            }
        } else {
            SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString ("cart", getAllFromCartStringFormat(context)+menu.toString()+"-"+qty+";");
            editor.apply();
        }
    }

    public static void removeFromCart(Context context, Menu menu){
        String cartString = getAllFromCartStringFormat(context);
        if(cartString.contains(menu.toString())){
            ArrayList<Order> orderList = getAllFromCart(context);
            emptyCart(context);
            for (Order order: orderList) {
                if(!order.getName().equals(menu.getName())){
                    addToCart(context, order.toMenu(), order.getQuantity());
                }
            }
        }
    }

    public static void emptyCart(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("cart");
        editor.apply();
    }

    private static String getAllFromCartStringFormat(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        String str = sharedPref.getString("cart", "");
        return str;
    }

    public static ArrayList<Order> getAllFromCart(Context context){
        String str = getAllFromCartStringFormat(context);
        ArrayList<Order> orderList = new ArrayList<>();

        if(!str.isEmpty()){
            String[] menus = str.split(";");
            for (String menu: menus) {
                String[] attributes = menu.split("-");
                orderList.add( new Order(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2])) );
            }
//        Log.v("TEST", orderList.toString());
        }

        return orderList;
    }

    public static int getTotalPrice(Context context) {
        ArrayList<Order> orders = getAllFromCart(context);
        int total = 0;
        for (Order order: orders) {
            total += order.getQuantity() * order.getPrice();
        }
        return total;
    }

    public static void topUp(Context context, int topUpAmount){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt ("wallet",  getWalletBalance(context) + topUpAmount);
        editor.apply();
    }

    public static void reduceBalance(Context context, int reduceAmount){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt ("wallet", (getWalletBalance(context) - reduceAmount));
        editor.apply();
    }

    public static int getWalletBalance(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        int bal = sharedPref.getInt("wallet", 0);
        return bal;
    }

}
