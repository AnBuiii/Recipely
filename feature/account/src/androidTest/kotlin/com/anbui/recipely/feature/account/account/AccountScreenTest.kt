package com.anbui.recipely.feature.account.account

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.anbui.recipely.core.testing.HiltComponentActivity
import com.anbui.recipely.core.testing.TestAccountRepository
import com.anbui.recipely.core.testing.TestCartRepository
import com.anbui.recipely.core.testing.TestRecipeRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@HiltAndroidTest
class AccountScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltComponentActivity>()

    val accountViewModel = AccountViewModel(
        accountRepository = TestAccountRepository(),
        recipeRepository = TestRecipeRepository(),
        cartRepository = TestCartRepository()
    )


    @Test
    fun initTest() {
        hiltRule.inject()
        composeTestRule.setContent {
            AccountRoute(
                onBack = {},
                onNavigateToSettingScreen = {},
                onNavigateToEditProfileScreen = {},
                onNavigateToOrderDetail = {},
                accountViewModel = accountViewModel
            )
        }
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.anbui.recipely.feature.account.test", appContext.packageName)
    }
}
