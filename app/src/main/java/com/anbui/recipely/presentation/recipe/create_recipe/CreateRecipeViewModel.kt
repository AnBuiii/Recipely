package com.anbui.recipely.presentation.recipe.create_recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.MediaType
import com.anbui.recipely.domain.models.Step
import com.anbui.recipely.domain.repository.RecipeRepository
import com.anbui.recipely.presentation.recipe.create_recipe.components.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CreateRecipeState())
    val state = _state.asStateFlow()

    fun onEvent(event: CreateRecipeEvent) {
        when (event) {
            is CreateRecipeEvent.EnterTitle -> {
                _state.update { it.copy(title = event.value) }
            }

            is CreateRecipeEvent.AddImage -> {
                _state.update { it.copy(coverImages = it.coverImages + event.value) }
            }

            is CreateRecipeEvent.EditImage -> {
                val newList = _state.value.coverImages.toMutableList()
                newList[event.index] = event.value

                _state.update { it.copy(coverImages = newList) }
            }

            is CreateRecipeEvent.RemoveImage -> {
                val newList = _state.value.coverImages.toMutableList()
                newList.removeAt(event.index)
                _state.update { it.copy(coverImages = newList) }
            }


            is CreateRecipeEvent.SelectIngredient -> {
//                if(_ingredients.any { it.ingredientId == event.value.id }){
//                    return
//                }
//                _ingredients.add(event.value)
            }

            is CreateRecipeEvent.SwapIngredient -> {
                try {
                    val newList = _state.value.ingredientItems.toMutableList()
                    newList.swap(event.from, event.to)
                    _state.update { it.copy(ingredientItems = newList) }
                } catch (_: Exception) {
                }
            }

            is CreateRecipeEvent.AddIngredient -> {
                viewModelScope.launch {
                    val index =
                        _state.value.ingredientItems.indexOfFirst { it.ingredientId == event.ingredientId }

                    if (index != -1) {
                        val items = _state.value.ingredientItems.toMutableList()
                        items[index] = items[index].copy(amount = event.amount.toFloat())
                        _state.update { it.copy(ingredientItems = items) }
                    } else {
                        val ingredient = recipeRepository.getIngredientById(event.ingredientId)
                        ingredient?.let {
                            IngredientItem(
                                ingredientId = ingredient.id,
                                name = ingredient.name,
                                amount = event.amount.toFloat(),
                                unit = ingredient.unit,
                                imageUrl = ingredient.imageUrl,
                                price = ingredient.price
                            )
                        }?.let { ingredientItem ->
                            _state.update { it.copy(ingredientItems = it.ingredientItems + ingredientItem) }
                        }
                    }
                }
            }

            is CreateRecipeEvent.SwapInstruction -> {
                try {
                    val newList = _state.value.steps.toMutableList()
                    newList.swap(event.from, event.to)
                    _state.update { it.copy(steps = newList) }
                } catch (e: Exception) {
                    Log.d("CreateRecipeViewModel", e.toString())
                }
            }

            is CreateRecipeEvent.AddInstruction -> {
                viewModelScope.launch {
                    val index =
                        _state.value.steps.indexOfFirst { it.id == event.instructionId }

                    if (index != -1) {
                        val items = _state.value.steps.toMutableList()
                        items[index] = items[index].copy(period = event.period.toLong())
                        _state.update { it.copy(steps = items) }
                    } else {
                        Step(
                            id = event.instructionId,
                            order = 0,
                            instruction = event.instruction,
                            mediaUrl = null,
                            type = MediaType.Image,
                            period = event.period.toLong()
                        ).let { step ->
                            _state.update { it.copy(steps = it.steps + step) }
                        }
                    }
                }
            }
        }
    }
}

