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
import com.example.myapplication5.model.Contact
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
   private val database=ContactDatabase.getDatabase(application)

    private val repository : ContactRepositoryImpl

    val contacts : LiveData<List<ContactEntity>>

    init {
        val contactDao=database.contactDao()
        repository=ContactRepositoryImpl(contactDao)
        contacts=repository.getAllContacts()
    }

    fun insert(contactEntity: ContactEntity){
        viewModelScope.launch {
            repository.insertContact(contactEntity = contactEntity)
        }
    }

    fun delete(contactEntity: ContactEntity){
        viewModelScope.launch {
            repository.deleteContact(contactEntity = contactEntity)
        }
    }
}