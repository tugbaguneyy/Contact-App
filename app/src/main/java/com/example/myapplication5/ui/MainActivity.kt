package com.example.myapplication5.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication5.navigation.NavigationGraph
import com.example.myapplication5.navigation.Screen
import com.example.myapplication5.presentation.components.BottomBar
import com.example.myapplication5.presentation.settings.SettingsViewModel
import com.example.myapplication5.ui.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = hiltViewModel<SettingsViewModel>()
            val isDarkMode = viewModel.isDarkMode.collectAsStateWithLifecycle()

            MyappTheme(
                darkTheme = isDarkMode.value
            ) {

                val navController = rememberNavController()
                val startDestination = Screen.Home
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                // Rota kontrolü için yardımcı fonksiyon
                fun isCurrentScreen(screen: KClass<out Screen>): Boolean {
                    return currentDestination?.hierarchy?.any {
                        it.route?.contains(screen.simpleName ?: "") == true
                    } == true
                }

                val isHomeScreen = isCurrentScreen(Screen.Home::class)


                Scaffold(
                       bottomBar = {
                           if(isHomeScreen){
                                BottomBar(navController)
                           }
                       }
                )
                    { innerPadding ->
                        NavigationGraph(
                            navController = navController,
                            startDestination = startDestination,
                            modifier = Modifier.padding(innerPadding)
                        )
                }
            }
        }
    }
}
