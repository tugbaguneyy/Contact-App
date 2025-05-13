package com.example.myapplication5.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication5.navigation.NavigationGraph
import com.example.myapplication5.navigation.Screen
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
            MyappTheme(darkTheme = false) {

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

                // Login veya Register sayfalarında olup olmadığımızı kontrol eden fonksiyon
                //val isAuthScreen = isCurrentScreen(Screen.Login::class) || isCurrentScreen(Screen.Register::class)

                Scaffold(
                    topBar = {
                        // Yalnızca Auth sayfalarında değilse TopBar'ı göster
                        //if (!isAuthScreen) {
                            val title = when {
                                isCurrentScreen(Screen.Home::class) -> "Contacts"
                                isCurrentScreen(Screen.Detail::class) -> "Details"
                                isCurrentScreen(Screen.Settings::class) -> "Settings"
                                isCurrentScreen(Screen.AddContact::class) -> "Add Contact"
                                else -> "Not Found"
                            }

                            CenterAlignedTopAppBar(
                                title = {
                                    Text(title)
                                },
                                navigationIcon = {
                                    if (!isCurrentScreen(Screen.Home::class)){
                                        IconButton(
                                            onClick = {
                                                navController.navigateUp()
                                            }
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color.Transparent,
                                    titleContentColor = Color.Black,
                                    navigationIconContentColor = Color.Black
                                )
                            )
                        //}
                    }
                    ) { innerPadding ->
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