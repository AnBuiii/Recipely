package com.anbui.recipely.presentation.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.model.Recipe
import com.anbui.model.exampleAccounts
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.NotificationRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val accountRepository: AccountRepository,
    private val notificationRepository: NotificationRepository
) : ViewModel() {
    val popularRecipe = recipeRepository.getAllRecipes().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        listOf()
    )

    val currentAccount = accountRepository.getCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        com.anbui.model.exampleAccounts[0]
    )

    fun likeRecipe(recipe: com.anbui.model.Recipe, like: Boolean) {
        viewModelScope.launch {
            recipeRepository.likeRecipe(recipe.id, like)
        }
    }
}
