package com.jeremyleonardo.ezyfood;

import java.io.Serializable;

public class Menu implements Serializable {

    private String name;
    private String type;
    private int price;
    private int image;

    public Menu(String name, String type, int price, int image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.image = image;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name + '-' + type + '-' + price + '-' + image;
    }
}
