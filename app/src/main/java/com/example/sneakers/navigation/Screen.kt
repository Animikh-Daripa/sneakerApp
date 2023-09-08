package com.example.sneakers.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController

sealed class Screen(val route: String, val title: String, val icon: ImageVector?) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Cart : Screen("cart", "Cart", Icons.Default.ShoppingCart)
    object Display : Screen("display", "Display", null)
}

object AppNavAction {
    fun navigate(navController: NavHostController, destination: String) {
        navController.navigate(destination)
    }
    fun navigateClearBackStack(navController: NavHostController, destination: String, route: String) {
        navController.navigate(destination) {
            popUpTo(route) { inclusive = true }
            launchSingleTop = true
        }
    }
}

//For bottom nav bar
val bottomBarItems = listOf(
    Screen.Home,
    Screen.Cart
)
