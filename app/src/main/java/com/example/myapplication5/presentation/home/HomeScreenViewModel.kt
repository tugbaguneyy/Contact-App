package com.example.myapplication5.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.usecase.GetAllContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase,
   // getRecentContactsUseCase: GetRecentContactsUseCase
) : ViewModel() {

   private val _allContacts= MutableStateFlow<List<ContactEntity>>(emptyList())
     val allContacts : StateFlow<List<ContactEntity>> =_allContacts.asStateFlow()

    private val _recentAdded = MutableStateFlow<List<ContactEntity>>(emptyList())
     val recentAdded : StateFlow<List<ContactEntity>> =_allContacts.asStateFlow()

    init {
        getAllContacts()
    }

    private fun getAllContacts(){
        viewModelScope.launch {
            getAllContactsUseCase().collect{
                _allContacts.value=it
                _recentAdded.value=it.take(6)
            }
        }
    }
}
