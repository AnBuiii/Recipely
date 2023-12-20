package com.anbui.recipely.feature.onboard.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
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

    fun changeState(value: State) {
        _state.update { it.copy(state = value) }
    }

    fun login() {
        viewModelScope.launch {
            _state.value.let {
                if (accountRepository.login(_state.value.email, _state.value.password)) {
                    changeState(State.Success)
                } else {
                    changeState(State.Fail)
                }

            }
        }
    }

}