package com.example.greenlantern.room.Models;

public class ReservesRooms {

    String roomId;
    String roomName;
    String roomPrice;
    String roomDescription;
    String roomImage;
    String userName;
    String userNIC;
    String userContact;
    String userAddress;

    public ReservesRooms() {
    }

    public ReservesRooms(String roomId, String roomName, String roomPrice, String roomDescription, String roomImage, String userName, String userNIC, String userContact, String userAddress) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomPrice = roomPrice;
        this.roomDescription = roomDescription;
        this.roomImage = roomImage;
        this.userName = userName;
        this.userNIC = userNIC;
        this.userContact = userContact;
        this.userAddress = userAddress;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(String roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(String roomImage) {
        this.roomImage = roomImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNIC() {
        return userNIC;
    }

    public void setUserNIC(String userNIC) {
        this.userNIC = userNIC;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}

