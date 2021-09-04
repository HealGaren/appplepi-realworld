package com.example.retrofit.api;

import com.example.retrofit.data.ArticleData;
import com.example.retrofit.data.ArticlesData;
import com.example.retrofit.data.SignInUserData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();

    @POST("/api/users/login")
    Call<SignInUserData> login(@Body SignInUserData signInUserData);
}
