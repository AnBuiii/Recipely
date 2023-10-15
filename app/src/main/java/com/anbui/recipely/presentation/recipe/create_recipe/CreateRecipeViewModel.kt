package com.anbui.recipely.presentation.recipe.create_recipe

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anbui.recipely.domain.models.Ingredient
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.domain.models.exampleIngredients
import com.anbui.recipely.domain.models.exampleSteps
import com.anbui.recipely.presentation.recipe.create_recipe.components.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor() : ViewModel() {
    private val _coverImages = mutableStateListOf<Uri?>()
    val images: List<Uri?> = _coverImages

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _searchResult = mutableStateListOf(*exampleIngredients.toTypedArray())
    val searchResult: List<Ingredient> = _searchResult

    private val _ingredients = mutableStateListOf<IngredientItem>()
    val ingredients: List<IngredientItem> = _ingredients

    private val _steps = mutableStateListOf<Step>(*exampleSteps.toTypedArray())
    val steps: List<Step> = _steps

//    private val sm = SharedV

    fun onEvent(event: CreateRecipeEvent) {
        when (event) {
            is CreateRecipeEvent.EnterTitle -> {
                _title.value = event.value
            }

            is CreateRecipeEvent.AddImage -> {
                _coverImages.add(event.value)
            }

            is CreateRecipeEvent.EditImage -> {
                _coverImages[event.index] = event.value
            }

            is CreateRecipeEvent.RemoveImage -> {
                _coverImages.removeAt(event.index)
            }

            is CreateRecipeEvent.EnterSearch -> {
                _searchText.value = event.value
            }

            is CreateRecipeEvent.SelectIngredient -> {
//                if(_ingredients.any { it.ingredientId == event.value.id }){
//                    return
//                }
//                _ingredients.add(event.value)
            }

            is CreateRecipeEvent.SwapIngredient -> {
                try {
                    _ingredients.swap(event.from, event.to)
                } catch (_: Exception) {
                }
            }

            is CreateRecipeEvent.AddIngredient -> {
                _ingredients.add(event.value)
            }

            is CreateRecipeEvent.SwapInstruction -> {
                try {
                    _steps.swap(event.from, event.to)
                } catch (_: Exception) {
                }
            }
        }
    }
}