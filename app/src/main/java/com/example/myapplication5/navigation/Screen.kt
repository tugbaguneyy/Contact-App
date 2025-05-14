package com.example.myapplication5.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Home : Screen

    @Serializable
    data class Detail(
        val name : String,
        val surname : String,
        val email : String,
        val image : String,
        val phone : String
    ) : Screen

    @Serializable
    data object AddContact : Screen

    @Serializable
    data object Settings : Screen


    @Serializable
    data object SignIn : Screen
}