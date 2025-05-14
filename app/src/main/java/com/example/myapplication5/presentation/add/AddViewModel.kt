package com.example.myapplication5.presentation.add

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication5.data.local.ContactEntity
import com.example.myapplication5.domain.usecase.InsertContactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val insertContactUseCase: InsertContactUseCase
) : ViewModel() {

    private val _formState = mutableStateOf(FormState())
    val formState: State<FormState> = _formState

    fun onFieldChange(
        name: String = _formState.value.name,
        surname: String = _formState.value.surname,
        email: String = _formState.value.email,
        phone: String = _formState.value.phone
    ) {
        val isNameValid = name.trim().isNotEmpty()
        val isSurnameValid = surname.trim().isNotEmpty()

        val emailNotEmpty = email.trim().isNotEmpty()
        val phoneNotEmpty = phone.trim().isNotEmpty()
        val atLeastOneContact = emailNotEmpty || phoneNotEmpty

        val isEmailValid = email.isEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPhoneValid = phone.isEmpty() || phone.matches(Regex("^\\+?[0-9]{10,15}\$"))

        val isFormValid = isNameValid && isSurnameValid && atLeastOneContact && isEmailValid && isPhoneValid

        _formState.value = FormState(
            name = name,
            surname = surname,
            email = email,
            phone = phone,
            isNameValid = isNameValid,
            isSurnameValid = isSurnameValid,
            isEmailValid = isEmailValid,
            isPhoneValid = isPhoneValid,
            isFormValid = isFormValid
        )
    }

    fun insert(contactEntity: ContactEntity) {
        viewModelScope.launch {
            insertContactUseCase(contactEntity)
        }
    }
}
