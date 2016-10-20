package com.kdao.realtor_meet_tinder.util;

public class Data {

    private String description;
    private int imagePath;
    private String address;
    private int price;

    public Data(int imagePath, String address, int price, String description) {
        this.imagePath = imagePath;
        this.address = address;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }
}
