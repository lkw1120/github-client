package com.lkw1120.client.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lkw1120.client.ui.detail.DetailScreen
import com.lkw1120.client.ui.search.SearchScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(
            route = NavScreen.SearchScreen.route
        ) {
            SearchScreen()
        }
        composable(
            route = NavScreen.DetailScreen.route
        ) {
            DetailScreen()
        }
    }
}