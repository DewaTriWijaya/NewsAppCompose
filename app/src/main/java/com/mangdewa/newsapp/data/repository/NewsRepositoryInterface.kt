package com.mangdewa.newsapp.data.repository

import com.mangdewa.newsapp.data.room.NewsEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryInterface {

    suspend fun newsList()

    suspend fun detail(title: String): Flow<List<NewsEntity>>

    suspend fun getFavoriteNews(): Flow<List<NewsEntity>>

    suspend fun setFavoriteNews(news: NewsEntity, fav: Boolean)

}