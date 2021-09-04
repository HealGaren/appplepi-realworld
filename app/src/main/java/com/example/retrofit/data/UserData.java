package com.example.retrofit.data;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("token")
    public String token;

    public UserData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
