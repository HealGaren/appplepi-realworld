package com.example.retrofit.api;

import com.example.retrofit.data.articledetail.ArticleDetailCommentData;
import com.example.retrofit.data.articledetail.ArticleDetailData;
import com.example.retrofit.data.ArticlesData;
import com.example.retrofit.data.NewUserRequest;
import com.example.retrofit.data.UserResponseData;
import com.example.retrofit.data.SignInUserData;
import com.example.retrofit.data.NewArticleData;
import com.example.retrofit.data.SingleArticleResponseData;
import com.example.retrofit.data.articledetail.ArticlesData;
import com.example.retrofit.data.articledetail.commentdata.CommentData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();

    @POST("/api/articles")
    Call<SingleArticleResponseData> postArticle(@Body NewArticleData newArticleData);

    @POST("/api/users/login")
    Call<SignInUserData> login(@Body SignInUserData signInUserData);

    @GET("/api/articles/{slug}")
    Call<ArticleDetailData> getArticleDetail(@Path("slug") String slug);

    @GET("/api/articles/{slug}/comments")
    Call<ArticleDetailCommentData> getArticleComment(@Path("slug") String slug);


    @POST("/api/users")
    Call<UserResponseData> signUp(@Body NewUserRequest user);

//    @POST("")
//    Call<ArticleDetailData> postArticle(@Body)
}
