package com.example.myapplication5.domain.usecase

import com.example.myapplication5.domain.repository.ContactDaoRepositoryImpl
import javax.inject.Inject

class SoftDeleteContactUseCase @Inject constructor(
    private val repository: ContactDaoRepositoryImpl
) {
    suspend operator fun invoke(id: Int) = repository.softDeleteContact(id)
}