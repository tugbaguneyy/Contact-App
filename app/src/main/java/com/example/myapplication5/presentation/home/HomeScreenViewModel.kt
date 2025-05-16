package com.example.myapplication5.presentation.home

import androidx.lifecycle.ViewModel
import com.example.myapplication5.domain.usecase.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
) :ViewModel() {

    val recentAdded = getAllContactsUseCase()
    val allContacts = getAllContactsUseCase()
}