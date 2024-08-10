package com.lkw1120.client.ui.navigation

sealed class NavScreen(val route: String) {
    data object SearchScreen : NavScreen(NavRoute.searchScreen)
    data object DetailScreen : NavScreen(NavRoute.detailScreen)
}