package com.example.myapplication5.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication5.navigation.Screen


data class BottomNavItem(
    val title : String,
    val icon : ImageVector,
    val screen : Screen
)

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomNavItem("Anasayfa", Icons.Default.Home, Screen.Home),
        BottomNavItem("Ekle", Icons.Default.Add, Screen.AddContact),
        BottomNavItem("Profil", Icons.Default.Person, Screen.Settings)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentDestination?.hierarchy?.any {
                    it.route?.contains(item.screen::class.simpleName ?: "") == true
                } == true,
                onClick = {
                    navController.navigate(item.screen) {
                        popUpTo(navController.graph.findStartDestination().route.toString()) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}