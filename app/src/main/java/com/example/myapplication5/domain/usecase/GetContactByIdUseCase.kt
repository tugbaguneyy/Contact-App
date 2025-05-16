package com.example.myapplication5.domain.usecase

import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetContactByIdUseCase @Inject constructor(
    private val repository: ContactDaoRepositoryImpl
) {
    operator fun invoke(id: Int) : Flow<ContactEntity> {
        return repository.getContactById(id)
    }
}