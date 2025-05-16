package com.example.myapplication5.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.usecase.GetContactByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getContactByIdUseCase: GetContactByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id") ?: 0

    private val _contact = MutableStateFlow<ContactEntity>(ContactEntity(0,"sges","dhdfhf","","",""))
    val contact : StateFlow<ContactEntity>
        get() = _contact.asStateFlow()

    init {
        getContactById(id)
    }

    fun getContactById(id: Int) {
        viewModelScope.launch {
            getContactByIdUseCase.invoke(id).collect {
                _contact.value = it
            }
        }
    }
}