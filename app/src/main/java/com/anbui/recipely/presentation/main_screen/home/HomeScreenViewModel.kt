package com.anbui.recipely.presentation.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.exampleAccounts
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {
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

    fun likeRecipe(recipeId: String, like: Boolean) {
        viewModelScope.launch {
            recipeRepository.likeRecipe(recipeId, like)
        }
    }

}
