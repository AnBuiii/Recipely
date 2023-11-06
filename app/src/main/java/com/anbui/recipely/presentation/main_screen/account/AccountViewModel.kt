package com.anbui.recipely.presentation.main_screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.exampleAccounts
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    accountRepository: AccountRepository,
    recipeRepository: RecipeRepository,
//    currentPreferences: CurrentPreferences,
) : ViewModel() {
    val currentAccount = accountRepository.getCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        exampleAccounts[0]
    )
    private val _favouriteRecipes = MutableStateFlow<List<Recipe>>(emptyList())
    val favouriteRecipes =  _favouriteRecipes.asStateFlow()

    init {
        viewModelScope.launch {
            recipeRepository.getFavouriteOfAccountId(
                accountRepository.getCurrentAccount().first().id
            ).collectLatest {
                _favouriteRecipes.emit(it)
            }
        }

    }
//    val favouriteRecipes =

}