package com.example.myapplication5.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication5.data.local.ContactDao
import com.example.myapplication5.data.local.ContactEntity

class ContactRepositoryImpl(private val contactDao: ContactDao) {

    suspend fun insertContact(contactEntity: ContactEntity){
        contactDao.insertContact(contactEntity = contactEntity)
    }

    fun getAllContacts() : LiveData<List<ContactEntity>> {
        return contactDao.getAllContacts()
    }

    suspend fun deleteContact(contactEntity: ContactEntity){
        contactDao.deleteContact(contactEntity = contactEntity)
    }

}