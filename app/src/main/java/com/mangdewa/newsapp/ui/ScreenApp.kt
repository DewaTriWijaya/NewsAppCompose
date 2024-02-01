package com.mangdewa.newsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mangdewa.newsapp.R
import com.mangdewa.newsapp.ui.navigation.NavigationItem
import com.mangdewa.newsapp.ui.navigation.Screen
import com.mangdewa.newsapp.ui.screen.detail.DetailsScreen
import com.mangdewa.newsapp.ui.screen.favorite.FavoriteScreen
import com.mangdewa.newsapp.ui.screen.home.HomeScreen
import com.mangdewa.newsapp.ui.screen.profile.ProfileScreen

@Composable
fun ScreenApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailNews.route) {
                BottomBar(navHostController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = {
                        navHostController.navigate(Screen.DetailNews.createRoute(it))
                    },
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = {
                        navHostController.navigate(Screen.DetailNews.createRoute(it))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailNews.route,
                arguments = listOf(navArgument("title") { type = NavType.StringType }),
            ){
                val id = it.arguments?.getString("title")
                DetailsScreen(
                    title = id!!,
                    navigateBack = { navHostController.navigateUp() }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.Person,
                screen = Screen.Profile
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navHostController.navigate(item.screen.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}