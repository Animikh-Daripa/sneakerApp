package com.example.sneakers.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.sneakers.R

@Composable
fun BottomBar() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                cutoutShape = CircleShape,
                backgroundColor = colorResource(id = R.color.primary),
                elevation = 22.dp
            ) {
                BottomNav(navController = navController)
            }
        }
//        ,
//        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
//        floatingActionButton = {
//            FloatingActionButton(
//                shape = CircleShape,
//                onClick = {
//                    Screen.Home.route.let {
//                        navController.navigate(it) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                    Screen.Home.route.let { navController.navigate(it) }
//                },
//                contentColor = androidx.compose.ui.graphics.Color.White,
//                backgroundColor = colorResource(id = R.color.purple_700)
//            ) {
//                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home icon")
//            }
//        }
    ) {
        SneakersNavGraph(modifier = Modifier.padding(it), navController = navController)
    }
}

