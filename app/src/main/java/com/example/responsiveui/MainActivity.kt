package com.example.responsiveui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.suite.ExperimentalMaterial3AdaptiveNavigationSuiteApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.responsiveui.navigation.HOME_ROUTE
import com.example.responsiveui.navigation.ROOT_ROUTE
import com.example.responsiveui.navigation.rememberAppState
import com.example.responsiveui.ui.AdaptiveApp
import com.example.responsiveui.ui.theme.ResponsiveUITheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveNavigationSuiteApi::class,
        ExperimentalMaterial3AdaptiveApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiveUITheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                    AdaptiveApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val appState = rememberAppState()

    Scaffold(
        bottomBar = {
            if(appState.shouldShowBottomBar) {
                BottomAppBar(

                ) {

                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = appState.navController,
            startDestination = HOME_ROUTE,
            route = ROOT_ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            //navGraph()
        }
    }
}
