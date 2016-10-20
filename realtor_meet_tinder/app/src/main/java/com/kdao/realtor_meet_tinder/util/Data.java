package com.kdao.realtor_meet_tinder.util;

public class Data {

    private String description;
    private String imagePath;
    private String address;
    private int price;

    public Data(String imagePath, String address, int price, String description) {
        this.imagePath = imagePath;
        this.address = address;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }
}
