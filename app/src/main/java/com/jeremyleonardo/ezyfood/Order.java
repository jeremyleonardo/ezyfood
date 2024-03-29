package com.jeremyleonardo.ezyfood;

import android.os.Parcel;
import android.os.Parcelable;

public class Order extends Menu implements Parcelable {

    private int quantity;

    public Order(String name, String type, int price, int image, int quantity) {
        super(name, type, price, image);
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
        return getName() + '-' + getType() + '-' + getPrice() + '-' + getImage() + '-' + quantity;
    }

    public Menu toMenu() {
        return new Menu(getName(), getType(), getPrice(), getImage());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[] {
                getName(),
                getType(),
                String.valueOf(getPrice()),
                String.valueOf(getImage()),
                String.valueOf(getQuantity())});
    }

    public Order(Parcel in){
        super();
        String[] data = new String[4];
        in.readStringArray(data);
        setName(data[0]);
        setType(data[1]);
        setPrice(Integer.parseInt(data[2]));
        setImage(Integer.parseInt(data[3]));
        setQuantity(Integer.parseInt(data[4]));
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
