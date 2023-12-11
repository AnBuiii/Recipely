package com.anbui.recipely.presentation.auth.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.Account
import com.anbui.recipely.domain.models.GenderType
import com.anbui.recipely.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class CreateAccountScreenViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountScreenState())
    val uiState = _uiState.asStateFlow()

    fun changeEmail(value: String) {
        _uiState.update { it.copy(email = value) }
    }

    fun changeFirstName(value: String) {
        _uiState.update { it.copy(firstName = value) }
    }

    fun changeLastName(value: String) {
        _uiState.update { it.copy(lastName = value) }
    }

    fun changePassword(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun changeConfirmPassword(value: String) {
        _uiState.update { it.copy(rePassword = value) }
    }

    fun changePasswordVisible(value: Boolean) {
        _uiState.update { it.copy(passwordVisible = value) }
    }

    fun changeConfirmPasswordVisible(value: Boolean) {
        _uiState.update { it.copy(rePasswordVisible = value) }
    }

    fun createAccount() {
        viewModelScope.launch {
            with(_uiState.value) {
                if (_uiState.value.password == _uiState.value.rePassword) {
                    val account = Account(
                        id = "",
                        firstName = firstName,
                        lastName = lastName,
                        email = email,
                        password = password,
                        bio = "",
                        avatarUrl = "",
                        dob = LocalDateTime.now(),
                        gender = GenderType.Male,
                        street = "",
                        district = "",
                        province = ""
                    )
                    if (accountRepository.addAccount(account)) {
                        accountRepository.login(account.email, account.password)
                        _uiState.update { it.copy(state = SuccessState.Success) }
                    } else {
                        _uiState.update { it.copy(state = SuccessState.Fail.Email) }
                    }
                } else{
                    _uiState.update { it.copy(state = SuccessState.Fail.Password) }
                }
            }
        }
    }
}