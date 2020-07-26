package com.example.project.model;

public class UserInformation {
    private int UserID;
    private String FullName;
    private String Email;
    private int Age;

    public UserInformation() {
    }

    public UserInformation(int userID, String fullName, String address, int age) {
        UserID = userID;
        FullName = fullName;
        Email = address;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
