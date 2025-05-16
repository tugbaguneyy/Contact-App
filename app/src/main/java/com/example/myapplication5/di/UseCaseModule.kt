package com.example.myapplication5.di

import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import com.example.myapplication5.domain.usecase.GetAllContactsUseCase
import com.example.myapplication5.domain.usecase.GetContactByIdUseCase
import com.example.myapplication5.domain.usecase.InsertContactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule{

    @Provides
    @Singleton
    fun provideInsertContactUseCase(repositoryImpl: ContactDaoRepositoryImpl) : InsertContactUseCase{
        return InsertContactUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetAllContactsUseCase(repositoryImpl: ContactDaoRepositoryImpl) : GetAllContactsUseCase {
        return GetAllContactsUseCase(repositoryImpl)
    }

    @Provides
    @Singleton
    fun provideGetContactByIdUseCase(repositoryImpl: ContactDaoRepositoryImpl) : GetContactByIdUseCase {
        return GetContactByIdUseCase(repositoryImpl)

    }


}