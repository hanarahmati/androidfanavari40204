package com.example.myapplication.compose

import androidx.appcompat.widget.Toolbar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.compose.home.HomeScreen
import com.example.myapplication.compose.home.SunflowerPage

@Composable
fun SunflowerApp(
    onAttached: (Toolbar) -> Unit = {},
) {
    val navController = rememberNavController()
    SunFlowerNavHost(
        navController = navController,
        onAttached = onAttached
    )
}

@Composable
fun SunFlowerNavHost(
    navController: NavHostController,
    onPageChange: (SunflowerPage) -> Unit = {},
    onAttached: (Toolbar) -> Unit = {},
) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPageChange = onPageChange,
                onAttached = onAttached,
            )
        }
    }
}