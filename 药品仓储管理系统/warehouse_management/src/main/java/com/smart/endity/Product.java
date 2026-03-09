package com.smart.endity;

public class Product {
    private String id;
    private String name;
    private double price;
    private int stock;
    private String remark;

    public Product() {
    }

    public Product(String id, String name, double price, int stock, String remark) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + stock + "," + remark;
    }
}
