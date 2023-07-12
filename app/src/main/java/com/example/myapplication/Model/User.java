package com.example.myapplication.Model;

public class User {
     String fullName,email,password,uid;

    public User() {
    }

    public User(String fullName, String email, String password,String uid) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
