package com.anbui.recipely.feature.account.my_recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
): ViewModel() {
    val currentRecipes = recipeRepository.getAllRecipeOfCurrentAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(1000),
        emptyList()
    )

    fun deleteRecipe(recipeId: String){
        viewModelScope.launch {
            Log.d("asdasd", recipeId)
            recipeRepository.deleteRecipe(recipeId)
        }
    }
}