package com.example.project.model;

public class UserInformation {
    private int UserID;
    private String FullName;
    private String Address;
    private int Age;

    public UserInformation() {
    }

    public UserInformation(int userID, String fullName, String address, int age) {
        UserID = userID;
        FullName = fullName;
        Address = address;
        Age = age;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
