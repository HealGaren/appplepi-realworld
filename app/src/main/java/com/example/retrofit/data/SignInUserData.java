package com.example.retrofit.data;

import com.google.gson.annotations.SerializedName;

public class SignInUserData {
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;
/*
    @SerializedName("token")
    public String token;*/

    public SignInUserData(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
