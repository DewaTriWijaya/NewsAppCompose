package com.mangdewa.newsapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangdewa.newsapp.data.repository.NewsRepository
import com.mangdewa.newsapp.data.room.NewsEntity
import com.mangdewa.newsapp.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repo: NewsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<DataState<List<NewsEntity>>> =
        MutableStateFlow(DataState.Loading)
    val uiState: StateFlow<DataState<List<NewsEntity>>>
        get() = _uiState


    fun newsDetail(title: String) {
        viewModelScope.launch {
            repo.detail(title)
                .catch {
                    _uiState.value = DataState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = DataState.Success(it)
                }
        }
    }

    fun setFavorite(surahEntity: NewsEntity, fav: Boolean) {
        viewModelScope.launch {
            repo.setFavoriteNews(surahEntity, fav)
        }
    }
}