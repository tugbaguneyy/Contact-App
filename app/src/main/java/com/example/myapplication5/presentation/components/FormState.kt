package com.example.myapplication5.presentation.components

data class FormState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val phone: String = "",
    val image: String = "",
    val isNameValid: Boolean = true,
    val isSurnameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val isPhoneValid: Boolean = true,
    val isFormValid: Boolean = false,
    val isImageValid: Boolean = true
)
