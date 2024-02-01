package com.mangdewa.newsapp.network

import retrofit2.http.GET

interface ApiService {
    @GET("top-headlines?country=us&apiKey=d48faa3c08b14f10a1f86a391a735b2e")
    suspend fun getAllNews(): ResponseArticle
}