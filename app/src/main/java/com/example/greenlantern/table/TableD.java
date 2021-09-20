package com.example.greenlantern.table;

public class TableD {
    private String tableNo;
    private String forName;
    private String phoneNum;
    private String total;
    private String amount;
    private String reservedDate;
    private String userId;

    public TableD(){}

    public TableD(String userId,String tableNo, String forName, String phoneNum, String total, String amount,String reservedDate) {
        this.tableNo = tableNo;
        this.forName = forName;
        this.phoneNum = phoneNum;
        this.total = total;
        this.amount = amount;
        this.reservedDate=reservedDate;
        this.userId=userId;

    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public String getForName() {
        return forName;
    }

    public void setForName(String forName) {
        this.forName = forName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(String reservedDate) {
        this.reservedDate = reservedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
