package com.example.responsiveui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

const val HOME_ROUTE = "home"
const val ROOT_ROUTE = "root"
const val SEARCH_ROUTE = "search"

sealed class Destinations(
    val route: String,
    val label: String? = null,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null
) {
    object HomeScreen: Destinations(
        "home_screen",
        "Home",
        Icons.Filled.Home,
        Icons.Outlined.Home
    )
    object DetailsScreen: Destinations(
        "bookmarks_screen",
        "Bookmarks",
        Icons.Filled.Info,
        Icons.Outlined.Home
    )
    object SearchScreen: Destinations(
        "search_screen",
        "Search",
        Icons.Filled.Search,
        Icons.Outlined.Search
    )
//    object HomeScreen: Destinations("home_screen")
}