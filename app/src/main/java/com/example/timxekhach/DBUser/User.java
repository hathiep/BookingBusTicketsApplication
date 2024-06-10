package com.example.timxekhach.DBUser;

public class User {
    private int UserID;
    private String Account, Fullname, Gmail, Password, Status;

    public User() {
    }

    public User(int userID, String account, String fullname, String gmail, String password, String status) {
        UserID = userID;
        Account = account;
        Fullname = fullname;
        Gmail = gmail;
        Password = password;
        Status = status;
    }

    public User(String account, String fullname, String gmail, String password, String status) {
        Account = account;
        Fullname = fullname;
        Gmail = gmail;
        Password = password;
        Status = status;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }
}
