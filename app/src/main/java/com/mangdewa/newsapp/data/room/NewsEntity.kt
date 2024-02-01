package com.mangdewa.newsapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_information")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("author")
    val author: String?,

    @ColumnInfo("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("description")
    val description: String?,

    @ColumnInfo("url")
    val url: String?,

    @ColumnInfo("content")
    val content: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean,


    )

fun List<NewsEntity>.asDomainModel(): List<NewsEntity> {
    return map {
        NewsEntity(
            title = it.title,
            publishedAt = it.publishedAt,
            author = it.author,
            urlToImage = it.urlToImage,
            description = it.description,
            url = it.url,
            content = it.content,
            favorite = it.favorite
        )
    }
}