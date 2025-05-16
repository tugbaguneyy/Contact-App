package com.example.myapplication5.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class ContactEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int=0,

    @ColumnInfo(name = "name")
    val name : String,

    @ColumnInfo(name = "surname")
    val surname : String,

    @ColumnInfo(name = "email")
    val email : String,

    @ColumnInfo(name = "image")
    val image : String,

    @ColumnInfo(name = "phone")
    val phone : String,

    @ColumnInfo(name = "isDeleted")
    val isDeleted: Boolean = false
)