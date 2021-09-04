package com.example.retrofit.data.articledetail

data class Comment(
    val author: Author,
    val body: String,
    val createdAt: String,
    val id: Int,
    val updatedAt: String
)