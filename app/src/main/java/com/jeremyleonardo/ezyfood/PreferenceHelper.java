package com.jeremyleonardo.ezyfood;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Set;

public class PreferenceHelper {

    public static void addToCart(Context context, Menu menu, int qty){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString ("cart", getAllFromCartStringFormat(context)+menu.toString()+"-"+qty+";");
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

        String[] menus = str.split(";");
        for (String menu: menus) {
            String[] attributes = menu.split("-");
            orderList.add( new Order(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2])) );
//            Log.v("TEST", attributes[0]);
//            Log.v("TEST", attributes[1]);
//            Log.v("TEST", attributes[2]);
        }
        Log.v("TEST", orderList.toString());

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

}
