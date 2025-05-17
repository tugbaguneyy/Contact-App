package com.example.myapplication5.domain.usecase

import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import javax.inject.Inject

class PermanentDeleteContactUseCase @Inject constructor(
    private val repositoryImpl: ContactDaoRepositoryImpl
){
    suspend operator fun invoke(id: Int) {
        repositoryImpl.deleteContactById(id)
    }
}