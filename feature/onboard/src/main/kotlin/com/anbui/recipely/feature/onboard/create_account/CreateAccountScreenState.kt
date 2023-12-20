package com.anbui.recipely.feature.onboard.create_account

data class CreateAccountScreenState(
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val rePassword: String = "",
    val passwordVisible: Boolean = false,
    val rePasswordVisible: Boolean = false,
    val state: SuccessState = SuccessState.Init
)

sealed class SuccessState {
    data object Init : SuccessState()
    data object Success : SuccessState()
    sealed class Fail : SuccessState() {
        data object Password : Fail()
        data object Email : Fail()
    }
}
