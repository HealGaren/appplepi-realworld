package com.example.retrofit.data;

import com.google.gson.annotations.SerializedName;

public class SignInUserRequestData {

    @SerializedName("user")
    public SignInUserData userData;

    public SignInUserRequestData(SignInUserData userData) {
        this.userData = userData;
    }
}
