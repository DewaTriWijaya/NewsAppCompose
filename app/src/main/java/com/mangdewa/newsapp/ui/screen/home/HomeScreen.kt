package com.mangdewa.newsapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mangdewa.newsapp.data.room.NewsEntity
import com.mangdewa.newsapp.ui.component.CardNews
import com.mangdewa.newsapp.utils.DataState

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    homeViewModel.uiState.collectAsState(initial = DataState.Loading).value.let { uiState ->
        when (uiState) {
            is DataState.Loading -> {
                homeViewModel.quranList()
            }
            is DataState.Success -> {
                Log.d("DataSuccess", "${uiState.data}")
                HomeContent(
                    surahList = uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is DataState.Error -> {
                Log.d("DataError", "Error")
            }
        }
    }
}

@Composable
fun HomeContent(
    surahList: List<NewsEntity>,
    navigateToDetail: (String) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(surahList) {
            CardNews(
                title = it.title,
                image = it.urlToImage ?: "https://digitiket.com/blog/assets/images/nopic.png",
                modifier = Modifier.clickable {
                    navigateToDetail(it.title)
                }
            )
        }
    }
}