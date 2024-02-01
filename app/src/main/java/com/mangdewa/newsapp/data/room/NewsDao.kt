package com.mangdewa.newsapp.data.room

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(surah: List<NewsEntity>)

    @Query("SELECT * from news_information ORDER BY title ASC")
    fun getAllNews(): Flow<List<NewsEntity>>

    @Query("SELECT * from news_information WHERE title IN (:title)")
    fun getDetailNews(title: String): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news_information WHERE favorite = 1")
    fun getFavoriteNews(): Flow<List<NewsEntity>>

    @Update
    fun setFavorite(news: NewsEntity)

}

@Database(entities = [NewsEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract val newsDao: NewsDao
}