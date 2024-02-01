package com.anbui.recipely.feature.onboard.login

import com.anbui.recipely.core.testing.MainDispatcherRule
import com.anbui.recipely.core.testing.TestAccountRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ViewModelTest {
    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private lateinit var accountViewModel: LoginViewModel

    @Before
    fun setUp() {
        accountViewModel = LoginViewModel(accountRepository = TestAccountRepository())
    }


    @Test
    fun changeEmail() {
        accountViewModel.changeEmail("email")
        assertEquals("email", accountViewModel.state.value.email)
    }


    @Test
    fun changePassword() {
        accountViewModel.changePassword("password")
        assertEquals("password", accountViewModel.state.value.password)
    }

    @Test
    fun changePasswordVisibility() {
        accountViewModel.changePasswordVisibility(false)
        assertEquals(false, accountViewModel.state.value.passwordVisible)
    }

    @Test
    fun loginTest() {
        accountViewModel.changeEmail("anbui@gmail.com")
        accountViewModel.changePassword("anbui")
        accountViewModel.login()
        assertEquals(State.Success, accountViewModel.state.value.state)
    }


}