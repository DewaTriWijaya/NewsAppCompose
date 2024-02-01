package com.mangdewa.newsapp.utils

sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val errorMessage: String) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}