package com.anbui.recipely.feature.onboard.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.model.Account
import com.anbui.recipely.core.model.GenderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
                        dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
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
                } else {
                    _uiState.update { it.copy(state = SuccessState.Fail.Password) }
                }
            }
        }
    }
}