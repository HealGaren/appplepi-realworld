package com.example.retrofit.api;

import com.example.retrofit.data.ArticlesData;
import com.example.retrofit.data.NewArticleData;
import com.example.retrofit.data.SingleArticleResponseData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();

    //yunsoo
    @POST("/api/articles")
    Call<SingleArticleResponseData> postArticle(@Body NewArticleData newArticleData);
}
