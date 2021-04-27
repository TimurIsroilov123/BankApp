package com.example.finance.ui.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class VerificationViewModel : ViewModel() {
    private var _firstName = MutableStateFlow("")

    private val _password = MutableStateFlow("")


    val isSubmitEnabled: Flow<Boolean> = combine(_firstName, _password) { firstName, password ->
        val regexString = "[a-zA-Z]+"
        val isNameCorrect = firstName.matches(regexString.toRegex())
        val isPasswordCorrect = password.length > 8
        return@combine isNameCorrect and isPasswordCorrect
    }

    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}
