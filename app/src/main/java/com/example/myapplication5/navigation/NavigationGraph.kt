package com.example.myapplication5.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication5.navigation.Screen.Home
import com.example.myapplication5.navigation.Screen.Detail
import com.example.myapplication5.navigation.Screen.Settings
import com.example.myapplication5.ui.AddContactScreen
import com.example.myapplication5.ui.detail.DetailScreen
import com.example.myapplication5.ui.home.HomeScreen
import com.example.myapplication5.ui.settings.SettingsScreen

@Composable
fun NavigationGraph(
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Home> {
           HomeScreen(navController)
        }
        composable<Detail> {
            val args=it.toRoute<Detail>()
            DetailScreen(navController,
                name = args.name,
                email = args.email,
                surname = args.surname,
                image = args.image
            )
        }
        composable<Settings> {
           SettingsScreen()
        }
        composable<Screen.AddContact> {
            AddContactScreen(navController)
        }
    }
}