package com.example.myapplication5.di

import com.example.myapplication5.data.local.ContactDao
import com.example.myapplication5.data.local.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule{
    @Provides
    @Singleton
    fun providesContactDao(database: ContactDatabase) : ContactDao{
        return database.contactDao()
    }
}