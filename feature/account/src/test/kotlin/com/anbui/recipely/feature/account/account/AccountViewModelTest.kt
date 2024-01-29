package com.anbui.recipely.feature.account.account

import com.anbui.recipely.core.testing.TestAccountRepository
import com.anbui.recipely.core.testing.TestCartRepository
import com.anbui.recipely.core.testing.TestRecipeRepository
import org.junit.Before
import org.junit.Test

class AccountViewModelTest {

    lateinit var accountViewModel: AccountViewModel

    @Before
    fun setUp() {
        accountViewModel = AccountViewModel(
            accountRepository = TestAccountRepository(),
            recipeRepository = TestRecipeRepository(),
            cartRepository = TestCartRepository()
        )
    }

    @Test
    fun getCurrentAccount() {
    }

    @Test

    fun getFavouriteRecipes() {
    }

    @Test

    fun getComingOrder() {
    }
}