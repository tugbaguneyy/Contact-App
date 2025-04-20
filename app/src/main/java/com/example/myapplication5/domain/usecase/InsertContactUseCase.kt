package com.example.myapplication5.domain.usecase

import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import javax.inject.Inject

class InsertContactUseCase @Inject constructor(
    private val repository: ContactDaoRepositoryImpl
){
    suspend operator fun invoke(contact : ContactEntity){
        repository.insertContact(contact)
    }
}