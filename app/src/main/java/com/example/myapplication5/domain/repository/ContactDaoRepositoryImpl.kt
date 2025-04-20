package com.example.myapplication5.domain.repository

import androidx.lifecycle.LiveData
import com.example.myapplication5.data.local.ContactDao
import com.example.myapplication5.data.local.ContactEntity
import javax.inject.Inject

class ContactDaoRepositoryImpl @Inject constructor(
    private val contactDao: ContactDao
) {
    suspend fun insertContact(contact : ContactEntity){
        contactDao.insertContact(contact)
    }

    fun getAllContacts() : LiveData<List<ContactEntity>>  {
        return contactDao.getAllContacts()
    }
}