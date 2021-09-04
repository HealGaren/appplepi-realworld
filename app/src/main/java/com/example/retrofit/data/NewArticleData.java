package com.example.retrofit.data;

import java.util.List;

public class NewArticleData {
    public String title;
    public String description;
    public String body;
    public List<String> tagList;

    public NewArticleData(String title, String description, String body, List<String> tagList) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tagList = tagList;
    }
}
