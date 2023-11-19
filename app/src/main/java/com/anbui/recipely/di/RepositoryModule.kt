package com.anbui.recipely.di

import com.anbui.recipely.data.repository.AccountRepositoryImpl
import com.anbui.recipely.data.repository.CartRepositoryImpl
import com.anbui.recipely.data.repository.CurrentPreferencesImpl
import com.anbui.recipely.data.repository.RecipeRepositoryImpl
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.CartRepository
import com.anbui.recipely.domain.repository.CurrentPreferences
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCurrentPreferences(
        currentPreferencesImpl: CurrentPreferencesImpl
    ): CurrentPreferences

    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}