package com.example.myapplication5.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao{
    @Insert
    suspend fun insertContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM Contacts ORDER BY id DESC")
    fun getAllContacts() : LiveData<List<ContactEntity>>

    @Query("SELECT * FROM Contacts ORDER BY id DESC LIMIT 6")
    fun getLastSixContacts(): LiveData<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id")

    fun getContactById(id: Int): Flow<ContactEntity>

    @Delete
    fun deleteContact(contactEntity: ContactEntity)
}