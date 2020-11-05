package com.jeremyleonardo.ezyfood;

import java.io.Serializable;

public class Menu implements Serializable {

    private String name;
    private String type;
    private int price;

    public Menu(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Menu(){

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + '-' + type + '-' + price;
    }
}
