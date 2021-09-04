package com.example.retrofit.api;

import com.example.retrofit.data.articledetail.ArticleDetailCommentData;
import com.example.retrofit.data.articledetail.ArticleDetailData;
import com.example.retrofit.data.ArticlesData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();

    @GET("/articles/{slug}")
    Call<ArticleDetailData> getArticleDetail(@Path("slug") String slug);

    @GET("/articles/{slug}/comment")
    Call<ArticleDetailCommentData> getArticleComment(@Path("slug") String slug);

//    @POST("")
//    Call<ArticleDetailData> postArticle(@Body)
}
