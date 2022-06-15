package com.example.discogsMusicCollection;

import java.io.Serializable;

public class UserProfileParameters implements Serializable {
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userLastName;
    private String userPhone;
    private String userDirection;

    public UserProfileParameters(){
        this.userEmail = "userEmail";
        this.userPassword = "userPassword";
        this.userName = "userName";
        this.userLastName = "userLastName";
        this.userPhone = "userPhone";
        this.userDirection = "userDirection";
    }
    public UserProfileParameters(String userEmail, String userPassword,String userName, String userLastName, String userPhone, String userDirection){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userPhone = userPhone;
        this.userDirection = userDirection;
    }

    public String getUserEmail() {return userEmail;}
    public void setUserEmail(String userEmail) {this.userEmail =  userEmail;}
    public String getUserPassword() {return userPassword;}
    public void setUserPassword(String userPassword) {this.userPassword =  userPassword;}

    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName =  userName;}

    public String getUserLastName() {return userLastName;}
    public void setUserLastName(String userLastName) {this.userLastName =  userLastName;}

    public String getUserPhone() {return userPhone;}
    public void setUserPhone(String userPhone) {this.userPhone =  userPhone;}

    public String getUserDirection() {return userDirection;}
    public void setUserDirection(String userDirection) {this.userDirection =  userDirection;}

}
