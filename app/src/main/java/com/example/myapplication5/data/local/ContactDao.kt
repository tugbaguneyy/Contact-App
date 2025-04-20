package com.example.myapplication5.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDao{
    @Insert
    suspend fun insertContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM Contacts ORDER BY id DESC")
    fun getAllContacts() : LiveData<List<ContactEntity>>

    @Query("SELECT * FROM Contacts ORDER BY id DESC LIMIT 6")
    fun getLastSixContacts(): LiveData<List<ContactEntity>>

    @Delete
    fun deleteContact(contactEntity: ContactEntity)
}