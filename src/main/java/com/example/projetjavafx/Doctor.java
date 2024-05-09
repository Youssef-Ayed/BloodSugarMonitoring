package com.example.projetjavafx;

public class Doctor {
    private int id;
    private String name;
    private String location;

    private int phone;
    private int price;

    public Doctor() {
    }

    public Doctor(String name, String location, int phone, int price) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.price = price;
    }

    public Doctor(int id, String name, String location, int phone, int price) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
