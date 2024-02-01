package com.anbui.recipely.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.data.repository.RecipeRepository
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.core.model.exampleAccounts
import dagger.hilt.android.lifecycle.HiltViewModel
import getPlatform
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    accountRepository: AccountRepository
) : ViewModel() {
    val platform = getPlatform()
    val popularRecipe = recipeRepository.getAllRecipes().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        listOf()
    )

    val currentAccount = accountRepository.getCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        exampleAccounts[0]
    )

    fun likeRecipe(recipe: Recipe, like: Boolean) {
        viewModelScope.launch {
            recipeRepository.likeRecipe(recipe.id, like)
        }
    }
}
