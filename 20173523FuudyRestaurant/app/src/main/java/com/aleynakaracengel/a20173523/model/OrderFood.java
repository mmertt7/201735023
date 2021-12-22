package com.aleynakaracengel.a20173523.model;

public class OrderFood {
    private int id;
    private String imageurl;
    private String name;
    private double price;

    public OrderFood() {
    }

    public OrderFood(int id, String imageurl, String name, double price) {
        this.id = id;
        this.imageurl = imageurl;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
