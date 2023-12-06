package com.anbui.recipely.presentation.auth.login

data class LoginScreenState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val error: String? = null,
    val state: State = State.Init
)

enum class State {
    Init, Fail, Success
}