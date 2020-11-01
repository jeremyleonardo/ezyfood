package com.jeremyleonardo.ezyfood;

public class Order extends Menu {

    private int quantity;

    public Order(String name, int price, int quantity) {
        super(name, price);
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return getName() + '-' + getPrice() + '-' + quantity;
    }
}
