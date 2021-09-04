package com.example.retrofit.api;

import com.example.retrofit.data.ArticlesData;
import com.example.retrofit.data.NewUserRequest;
import com.example.retrofit.data.UserResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();


    @POST("/api/users")
    Call<UserResponseData> signUp(@Body NewUserRequest user);
}
