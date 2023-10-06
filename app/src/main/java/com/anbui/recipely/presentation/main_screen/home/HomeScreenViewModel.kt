package com.anbui.recipely.presentation.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    recipeRepository: RecipeRepository
) : ViewModel() {
    val popularRecipe = recipeRepository.getAllRecipes().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        listOf()
    )
}
