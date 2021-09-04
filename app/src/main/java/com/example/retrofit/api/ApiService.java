package com.example.retrofit.api;

import com.example.retrofit.data.articledetail.ArticleDetailCommentData;
import com.example.retrofit.data.articledetail.ArticleDetailData;
import com.example.retrofit.data.articledetail.ArticlesData;
import com.example.retrofit.data.articledetail.commentdata.CommentData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/api/articles")
    Call<ArticlesData> getArticle();

    @GET("/api/articles/{slug}")
    Call<ArticleDetailData> getArticleDetail(@Path("slug") String slug);

    @GET("/api/articles/{slug}/comments")
    Call<ArticleDetailCommentData> getArticleComment(@Path("slug") String slug);

    @GET("/api/articles/{slug}/comments")
    Call<CommentData> postArticleComment(@Path("slug") String slug,
                                         @Body String comment);

//    @POST("")
//    Call<ArticleDetailData> postArticle(@Body)
}
