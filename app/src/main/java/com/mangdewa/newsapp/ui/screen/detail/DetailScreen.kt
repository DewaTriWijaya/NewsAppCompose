package com.mangdewa.newsapp.ui.screen.detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.mangdewa.newsapp.ui.component.DetailContent
import com.mangdewa.newsapp.utils.DataState

@Composable
fun DetailsScreen(
    title: String,
    navigateBack: () -> Unit
) {
    val detailViewModel = hiltViewModel<DetailViewModel>()
    var setFav by rememberSaveable { mutableStateOf(false) }

    detailViewModel.uiState.collectAsState(initial = DataState.Loading).value.let { uiState ->
        when (uiState) {
            is DataState.Loading -> {
                detailViewModel.newsDetail(title)
            }
            is DataState.Success -> {
                val data = uiState.data
                data.forEach {
                    setFav = it.favorite
                    DetailContent(
                        title = it.title,
                        author = it.author ?: "Not Author",
                        description = it.description ?: "Not Description",
                        urlToImage = it.urlToImage ?: "https://digitiket.com/blog/assets/images/nopic.png",
                        publishedAt = it.publishedAt ?: "00.00.00",
                        favorite = {
                            val set = !it.favorite
                            detailViewModel.setFavorite(it, set)
                        },
                        setFav = setFav,
                        onBackClick = { navigateBack() }
                    )
                }
            }
            is DataState.Error -> {
                Log.d("Error", "Error Data")
            }
        }
    }
}