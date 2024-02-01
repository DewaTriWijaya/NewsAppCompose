package com.mangdewa.newsapp.ui.screen.favorite

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.mangdewa.newsapp.ui.screen.home.HomeContent
import com.mangdewa.newsapp.ui.screen.home.HomeViewModel
import com.mangdewa.newsapp.utils.DataState

@Composable
fun FavoriteScreen(
    navigateToDetail: (String) -> Unit,
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    homeViewModel.uiState.collectAsState(initial = DataState.Loading).value.let { uiState ->
        when (uiState) {
            is DataState.Loading -> {
                homeViewModel.favoriteList()
                Log.d("DataLoading", "Loading")
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
