package com.example.responsiveui.ui

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigation.suite.NavigationSuiteType
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.responsiveui.navigation.Destinations
import com.example.responsiveui.navigation.homeGraph
import com.example.responsiveui.navigation.rememberAppState

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    ExperimentalMaterial3AdaptiveNavigationSuiteApi::class
)
@Composable
fun AdaptiveApp() {
    var selected by remember {
        mutableStateOf(0)
    }
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val topDestinations = listOf<Destinations>()
    val customNavSuiteType = with(adaptiveInfo) {
        if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    }
    val navController = rememberAppState().navController

    NavigationSuiteScaffold(
        layoutType = customNavSuiteType,
        navigationSuiteItems = {
            topDestinations.forEachIndexed { index, destination ->
                item(
                    icon = {
                        Icon(
//                            imageVector = destination.selectedIcon!!,
//                            contentDescription = null
                            imageVector = if (selected == index) { //TODO it works?
                                destination.selectedIcon!!
                            } else destination.unselectedIcon!!,
                            contentDescription = destination.label
                        )
                    },
                    label = { Text(text = destination.label!!) },
                    selected = selected == index,
                    onClick = {
                        selected = index
                        navController.navigateSingleTopTo(destination.route)
                    }
                )
            }
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        NavHost(
            navController = navController,
            startDestination = Destinations.HomeScreen.route
        ) {
            //composable(homeGraph())
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }