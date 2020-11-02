package com.jeremyleonardo.ezyfood;

import android.os.Parcel;
import android.os.Parcelable;

public class Order extends Menu implements Parcelable {

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

    public Menu toMenu() {
        return new Menu(getName(), getPrice());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                getName(),
                String.valueOf(getPrice()),
                String.valueOf(getQuantity())});
    }

    public Order(Parcel in){
        super();
        String[] data = new String[3];
        in.readStringArray(data);
        setName(data[0]);
        setPrice(Integer.parseInt(data[1]));
        setQuantity(Integer.parseInt(data[3]));
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
