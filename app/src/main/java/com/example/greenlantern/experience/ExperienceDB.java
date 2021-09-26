package com.example.greenlantern.experience;

public class ExperienceDB {
    private String session_type;
    private String sessionID;
    private String userID;
    private String date;
    private String name;
    private String mobile;
    private int members;
    private String total;
    private String key;

    public ExperienceDB() {
    }

    public ExperienceDB(String session_type, String sessionID, String userID, String date, String name, String mobile, int members, String total) {
        this.session_type = session_type;
        this.sessionID = sessionID;
        this.userID = userID;
        this.date = date;
        this.name = name;
        this.mobile = mobile;
        this.members = members;
        this.total = total;
    }
    public String getSession_type() { return session_type; }

    public void setSession_type(String session_type) { this.session_type = session_type; }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}
