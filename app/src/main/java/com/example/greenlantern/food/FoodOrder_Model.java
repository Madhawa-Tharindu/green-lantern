package com.example.greenlantern.food;

public class FoodOrder_Model {

    private int pid;
    private String pname;
    private float total;
    private String imgurl;
    private int qty;
    private String mobile;
    private String tablenum;
    private String category;
    private String username;
    private String date;
    private String note;

    public FoodOrder_Model() {
    }

    public FoodOrder_Model(int pid, String pname, float total, String imgurl, int qty, String mobile, String tablenum, String category, String username, String date, String note) {
        this.pid = pid;
        this.pname = pname;
        this.total = total;
        this.imgurl = imgurl;
        this.qty = qty;
        this.mobile = mobile;
        this.tablenum = tablenum;
        this.category = category;
        this.username = username;
        this.date = date;
        this.note = note;
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public float getTotal() {
        return total;
    }

    public String getImgurl() {
        return imgurl;
    }

    public int getQty() {
        return qty;
    }

    public String getMobile() {
        return mobile;
    }

    public String getTablenum() {
        return tablenum;
    }

    public String getCategory() {
        return category;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }
}
//
