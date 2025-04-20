package com.example.myapplication5.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactDatabase
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.repository.ContactRepositoryImpl
import com.example.myapplication5.domain.usecase.GetAllContactsUseCase
import com.example.myapplication5.domain.usecase.InsertContactUseCase
import com.example.myapplication5.model.Contact
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