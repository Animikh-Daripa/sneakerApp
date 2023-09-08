package com.example.sneakers.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sneakers.cart.CartList
import com.example.sneakers.display.DisplayScreen
import com.example.sneakers.home.HomeModel
import com.example.sneakers.home.HomeScreen
import com.example.sneakers.home.HomeViewModel

@Composable
fun SneakersNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    val viewModel = HomeViewModel()
    var displayItemId:String =""
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(homeViewModel = viewModel, homeModel = HomeModel(
                onItemClick = {
                    displayItemId = it
                    AppNavAction.navigate(navController, Screen.Display.route)
                }
            ))
        }

        composable(Screen.Cart.route) {
            CartList(viewModel = viewModel, onBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.Display.route) {
            DisplayScreen(viewModel = viewModel,displayItemId, onBack = {
                navController.popBackStack()
            })
        }

    }
}