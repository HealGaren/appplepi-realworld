package com.example.retrofit.data;

import com.google.gson.annotations.SerializedName;

public class SignInUserData {

    @SerializedName("user")
    public UserData userData;

    public SignInUserData(UserData userData) {
        this.userData = userData;
    }
}
