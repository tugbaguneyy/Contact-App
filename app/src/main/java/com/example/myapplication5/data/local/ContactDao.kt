package com.example.myapplication5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao{
    @Insert
    suspend fun insertContact(contactEntity: ContactEntity)

    @Query("SELECT * FROM Contacts WHERE isDeleted = 0 ORDER BY id DESC")
    fun getAllContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): Flow<ContactEntity>

    @Query("UPDATE Contacts SET isDeleted = 1 WHERE id = :id")
    suspend fun softDeleteContact(id: Int)

    @Query("UPDATE Contacts SET isDeleted = 0 WHERE id = :id")
    suspend fun restoreContact(id: Int)

    @Query("DELETE FROM Contacts WHERE id = :id")
    suspend fun deleteContactById(id: Int)

    @Query("SELECT * FROM Contacts WHERE isDeleted = 1 ORDER BY id DESC")
    fun getDeletedContacts(): Flow<List<ContactEntity>>

    @Update
    suspend fun updateContact(contact: ContactEntity)

}