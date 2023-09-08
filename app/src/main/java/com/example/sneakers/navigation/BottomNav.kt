package com.example.sneakers.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sneakers.R

@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp)
            .height(100.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        bottomBarItems.forEach {
            var isSelected = false

            if(currentRoute?.route == it.route){
                isSelected = true
            }

            BottomNavigationItem(
                icon = {
                    it.icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                            //tint = Color.Gray

                        )
                    }
                },
                label = {
                    it.title.let {
                        Text(
                            text = it,
                            //color = Color.Gray
                        )
                    }
                },

                selected = isSelected,

                selectedContentColor = androidx.compose.ui.graphics.Color.Red,
                unselectedContentColor = androidx.compose.ui.graphics.Color.White,
                onClick = {
                    it.route.let { it1 ->
                        navController.navigate(it1) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = false
                        }
                    }
                }
            )
        }
    }
}