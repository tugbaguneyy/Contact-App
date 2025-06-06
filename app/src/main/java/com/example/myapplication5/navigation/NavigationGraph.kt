package com.example.myapplication5.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myapplication5.navigation.Screen.Home
import com.example.myapplication5.navigation.Screen.Detail
import com.example.myapplication5.navigation.Screen.Settings
import com.example.myapplication5.presentation.add.AddContactScreen
import com.example.myapplication5.presentation.auth.SignInScreen
import com.example.myapplication5.presentation.detail.DetailScreen
import com.example.myapplication5.presentation.dial.DialerScreen
import com.example.myapplication5.presentation.edit.EditContactScreen
import com.example.myapplication5.presentation.home.HomeScreen
import com.example.myapplication5.presentation.settings.SettingsScreen
import com.example.myapplication5.presentation.settings.recyclebin.RecycleBinScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Home> {
           HomeScreen(navController)
        }
        composable<Detail> {
            DetailScreen(navController)
        }
        composable<Settings> {
           SettingsScreen(navController)
        }
        composable<Screen.AddContact> {
            AddContactScreen(navController)
        }
        composable<Screen.SignIn> {
            SignInScreen(navController)
        }
        composable<Screen.Dialer> {
            DialerScreen(navController)
        }
        composable<Screen.RecycleBin> {
            RecycleBinScreen(navController)
        }
        composable<Screen.EditContact> {
            EditContactScreen(navController)
        }
    }
}