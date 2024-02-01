package com.mangdewa.newsapp.network

import com.google.gson.annotations.SerializedName
import com.mangdewa.newsapp.data.room.NewsEntity

data class ResponseArticle(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<NewsResponse>,
    @SerializedName("status")
    val status: String
)
data class NewsResponse(
    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String?,

    @SerializedName("content")
    val content: String?,
)

fun List<NewsResponse>.asDatabaseModel(): List<NewsEntity> {
    return map {
        NewsEntity(
            publishedAt= it.publishedAt,
            author= it.author,
            urlToImage= it.urlToImage,
            description= it.description,
            title = it.title,
            url = it.url,
            content = it.content,
            favorite = false
        )
    }
}