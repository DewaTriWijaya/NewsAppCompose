package com.mangdewa.newsapp.ui.navigation

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
    object DetailNews: Screen("Home/{title}"){
        fun createRoute(title: String) = "home/$title"
    }
}