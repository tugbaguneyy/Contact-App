package com.example.myapplication5.presentation.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.usecase.GetAllContactsUseCase
import com.example.myapplication5.domain.usecase.InsertContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val insertContactUseCase: InsertContactUseCase
) :ViewModel() {

    val recentAdded = getAllContactsUseCase()

    val allContacts = getAllContactsUseCase()

    fun insert(contactEntity: ContactEntity){
        viewModelScope.launch {
            insertContactUseCase(contactEntity)
        }
    }


}