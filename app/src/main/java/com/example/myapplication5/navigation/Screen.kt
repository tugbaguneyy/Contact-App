package com.example.myapplication5.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(
        val id : Int
    ) : Screen

    @Serializable
    data object AddContact : Screen

    @Serializable
    data object Settings : Screen

    @Serializable
    data object SignIn : Screen

    @Serializable
    data object Dialer : Screen
}