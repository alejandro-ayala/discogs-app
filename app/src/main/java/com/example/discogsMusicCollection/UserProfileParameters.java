package com.example.discogsMusicCollection;

public class UserProfileParameters {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userLastName;
    private String userPhone;
    private String userDirection;

    public UserProfileParameters(String userEmail, String userPassword,String userName, String userLastName, String userPhone, String userDirection){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userPhone = userPhone;
        this.userDirection = userDirection;
    }

    public String getUserEmail() {return userEmail;}
    public String getUserPassword() {return userPassword;}
    public String getUserName() {return userName;}
    public String getUserLastName() {return userLastName;}
    public String getUserPhone() {return userPhone;}
    public String getUserDirection() {return userDirection;}
}
