package com.example.responsiveui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost

@Composable
fun RootNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = ROOT_ROUTE
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {

    }
}