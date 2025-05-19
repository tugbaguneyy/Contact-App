package com.example.myapplication5.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllContactsUseCase @Inject constructor(
    private val repositoryImpl: ContactDaoRepositoryImpl
) {

    operator fun invoke() : Flow<List<ContactEntity>> {
        return repositoryImpl.getAllContacts()
    }
}