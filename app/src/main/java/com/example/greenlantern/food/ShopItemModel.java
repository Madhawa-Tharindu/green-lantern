package com.example.greenlantern.food;

public class ShopItemModel {
    int pid;
    private String foodname;
    private Double foodprice;
    private int image;
    private String category;

    public ShopItemModel(int pid,String foodname, Double foodprice, int image,String category) {
        this.pid = pid;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.image = image;
        this.category = category;
    }

    public String getFoodname() {
        return foodname;
    }

    public Double getFoodprice() {
        return foodprice;
    }

    public int getImage() {
        return image;
    }

    public int getPid() {
        return pid;
    }

    public String getCategory() {
        return category;
    }
}
