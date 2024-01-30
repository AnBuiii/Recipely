package com.anbui.recipely.feature.onboard.login

import android.util.Log
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import com.anbui.recipely.core.testing.HiltComponentActivity
import com.anbui.recipely.core.testing.TestAccountRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun initTest() {
        composeTestRule.setContent {
            LoginRoute(
                onNavigateToHome = {
                    Log.d("TAG", "home")
//                    assert(false)
                },
                onBack = {},
                onForgotPassword = {},
                loginViewModel = LoginViewModel(
                    accountRepository = TestAccountRepository()
                )

            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithContentDescription("emailTextField").onChild()
            .performTextInput("builehoaian")
        composeTestRule.onNodeWithContentDescription("passwordTextField").onChild()
            .performTextInput("builehoaian")

        composeTestRule.onNodeWithContentDescription("loginButton").performClick()
//        composeTestRule.onNodeWithContentDescription("loginButton").performClick()
//        composeTestRule.onNodeWithContentDescription("loginButton").performClick()
//        composeTestRule.onNodeWithContentDescription("loginButton").performClick()
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.anbui.recipely.feature.onboard.test", appContext.packageName)
    }
}