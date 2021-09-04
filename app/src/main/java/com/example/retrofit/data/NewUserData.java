package com.example.retrofit.data;

public class NewUserData {
    public  String username;

    public NewUserData(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public  String email;
    public  String password;
}
