package com.example.myapplication5.presentation.settings.recyclebin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.usecase.GetDeletedContactsUseCase
import com.example.myapplication5.domain.usecase.RestoreContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecycleBinViewModel @Inject constructor(
    private val getDeletedContactsUseCase: GetDeletedContactsUseCase,
    private val restoreContactUseCase: RestoreContactUseCase
) : ViewModel() {

    private val _deletedContacts = MutableStateFlow<List<ContactEntity>>(emptyList())
    val deletedContacts: StateFlow<List<ContactEntity>> = _deletedContacts.asStateFlow()

    init {
        loadDeletedContacts()
    }

    private fun loadDeletedContacts() {
        viewModelScope.launch {
            getDeletedContactsUseCase().collect { contacts ->
                _deletedContacts.value = contacts
            }
        }
    }

    fun restoreContact(id: Int) {
        viewModelScope.launch {
            restoreContactUseCase(id)
            loadDeletedContacts() // refresh
        }
    }
}



