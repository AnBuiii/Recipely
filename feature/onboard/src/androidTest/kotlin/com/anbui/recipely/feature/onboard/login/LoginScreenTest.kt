package com.anbui.recipely.feature.onboard.login

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.anbui.recipely.core.testing.HiltComponentActivity
import com.anbui.recipely.core.testing.TestAccountRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        loginViewModel = LoginViewModel(
            accountRepository = TestAccountRepository()
        )
    }

    @Test
    fun loginTestFail() {
        composeTestRule.setContent {
            LoginRoute(
                onNavigateToHome = {},
                onBack = {},
                onForgotPassword = {},
                loginViewModel = loginViewModel
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("emailTextField").onChild()
            .performTextInput("builehoaian")
        composeTestRule.onNodeWithContentDescription("passwordTextField").onChild()
            .performTextInput("builehoaian")

        composeTestRule.onNodeWithContentDescription("loginButton").performClick()

        assertEquals(State.Fail, loginViewModel.state.value.state)
    }

    @Test
    fun loginTestSuccess() {
        composeTestRule.setContent {
            LoginRoute(
                onNavigateToHome = {},
                onBack = {},
                onForgotPassword = {},
                loginViewModel = loginViewModel
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("emailTextField").onChild()
            .performTextInput("anbui@gmail.com")
        composeTestRule.onNodeWithContentDescription("passwordTextField").onChild()
            .performTextInput("anbui")

        composeTestRule.onNodeWithContentDescription("loginButton").performClick()

        assertEquals(State.Success, loginViewModel.state.value.state)
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.anbui.recipely.feature.onboard.test", appContext.packageName)
    }
}