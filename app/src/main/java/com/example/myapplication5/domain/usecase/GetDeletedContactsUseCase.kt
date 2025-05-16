package com.example.myapplication5.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDeletedContactsUseCase @Inject constructor(
    private val repository: ContactDaoRepositoryImpl
) {
    operator fun invoke(): Flow<List<ContactEntity>> {
        return repository.getDeletedContacts()
    }
}