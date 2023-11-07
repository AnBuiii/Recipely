package com.anbui.recipely.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun changeEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun changePassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun changePasswordVisibility(value: Boolean) {
        _state.update { it.copy(passwordVisible = value) }
    }

    fun changeError(value: String?) {
        _state.update { it.copy(error = value) }
    }

    fun changeSuccess(value: Boolean) {
        _state.update { it.copy(success = value) }
    }


    fun login() {
        viewModelScope.launch {
            _state.value.let {
                accountRepository.login("builehoaian2002@gmail.com", "builehoaian")
                changeSuccess(true)
//                if (accountRepository.login(it.email, it.password)) {
//                   changeSuccess(true)
//                } else {
//                    changeError("Login fail")
//                }

            }
        }
    }

}