package com.example.demo;

public class display  {
    public String name;
    public Integer price;
    public String brand;
    public display(String name, Integer price, String brand) {
        this.name = name;
        this.price = price;
        this.brand = brand;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
