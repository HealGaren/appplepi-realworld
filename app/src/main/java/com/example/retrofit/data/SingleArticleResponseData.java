package com.example.retrofit.data;

import com.example.retrofit.data.articledetail.AuthorData;

import java.util.List;

public class SingleArticleResponseData {
    public String slug;
    public String title;
    public String description;
    public String body;
    public List<String> tagList = null;
    public String createdAt;
    public String updatedAt;
    public Boolean favorited;
    public Integer favoritesCount;
    public AuthorData authorData;

}
