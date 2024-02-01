package com.mangdewa.newsapp.data.repository

import android.util.Log
import com.mangdewa.newsapp.data.room.AppDatabase
import com.mangdewa.newsapp.data.room.NewsEntity
import com.mangdewa.newsapp.data.room.asDomainModel
import com.mangdewa.newsapp.network.ApiService
import com.mangdewa.newsapp.network.asDatabaseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : NewsRepositoryInterface {

    val news: Flow<List<NewsEntity>> =
        appDatabase.newsDao.getAllNews().map { it.asDomainModel() }

    val favoriteNews: Flow<List<NewsEntity>> =
        appDatabase.newsDao.getFavoriteNews().map { it.asDomainModel() }

    override suspend fun detail(title: String): Flow<List<NewsEntity>> =
        appDatabase.newsDao.getDetailNews(title).map { it.asDomainModel() }

    override suspend fun newsList() {
        try {
            val surah = apiService.getAllNews().articles
            appDatabase.newsDao.insertNews(surah.asDatabaseModel())
        } catch (e: Exception) {
            Log.d("Data Error", "${e.message}")
        }
    }

    override suspend fun getFavoriteNews(): Flow<List<NewsEntity>> {
        return appDatabase.newsDao.getFavoriteNews()
    }

    override suspend fun setFavoriteNews(news: NewsEntity, fav: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            news.favorite = fav
            appDatabase.newsDao.setFavorite(news)
        }
    }

}