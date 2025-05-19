package com.example.myapplication5.model

data class Contact(
    val id: Int = 0,
    val name: String,
    val surname: String,
    val email: String,
    val image: String,
    val phone: String,
    val isDeleted: Boolean = false
)

