package com.anbui.recipely.feature.create_recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import com.anbui.recipely.core.model.MediaType
import com.anbui.recipely.core.model.Step
import com.anbui.recipely.core.model.toIngredientItem
import com.anbui.recipely.feature.create_recipe.add_ingredient.AddIngredientEvent
import com.anbui.recipely.feature.create_recipe.add_ingredient.AddIngredientState
import com.anbui.recipely.feature.create_recipe.components.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
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
                        ingredient?.toIngredientItem(event.amount.toFloat())
                            ?.let { ingredientItem ->
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
                        items[index] = items[index].copy(
                            period = event.period.toLong(),
                            instruction = event.instruction
                        )
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

            is CreateRecipeEvent.EditDescription -> {
                _state.update { it.copy(description = event.value) }
            }

            is CreateRecipeEvent.EditServings -> {
                _state.update { it.copy(serving = event.value) }
            }

            CreateRecipeEvent.CreateRecipe -> {
                viewModelScope.launch {
                    with(_state.value) {
                        try {
                            recipeRepository.createRecipe(
                                title = title,
                                imageUrl = coverImages.firstOrNull().toString(),
                                description = description,
                                servings = serving.toInt(),
                                ingredients = ingredientItems,
                                steps = steps
                            )
                            _state.update { it.copy(success = true) }
                        } catch (_: Exception) {

                        }
                    }
                }
            }
        }
    }

    private val _addIngredientState = MutableStateFlow(AddIngredientState())
    val addIngredientState = _addIngredientState.asStateFlow()

    private val _ingredientName = MutableStateFlow("")
    val ingredientName = _ingredientName.asStateFlow()

    val ingredients = _ingredientName
        .debounce(300)
        .onEach { _addIngredientState.update { it.copy(isSearching = true) } }
        .map(recipeRepository::searchIngredients)
        .onEach { _addIngredientState.update { it.copy(isSearching = false) } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    fun onAddIngredientEvent(event: AddIngredientEvent) {
        fun onChangeIngredientName(newValue: String) {
            _ingredientName.update { newValue }
        }

        fun onChangeUnit(newValue: String) {
            _addIngredientState.update { it.copy(unit = newValue) }
        }
        when (event) {
            is AddIngredientEvent.EnterIngredientName -> {
                _ingredientName.update { event.value }
            }

            is AddIngredientEvent.EnterAmount -> {
                _addIngredientState.update { it.copy(amount = event.value) }
            }

            is AddIngredientEvent.AddIngredient -> {
                with(_addIngredientState.value) {
                    if (selectedIngredientId.isNotEmpty()) {
                        try {
                            amount.toDouble()
                            _addIngredientState.update { it.copy(success = true) }
                        } catch (_: Exception) {
                            _addIngredientState.update { it.copy(error = "Wrong amount") }
                            _addIngredientState.update { it.copy(error = null) }
                            return
                        }
                    } else {
                        Log.d("asd", "")
                    }
                }
            }

            is AddIngredientEvent.ChooseIngredient -> {
                onChangeIngredientName(event.ingredient.name)
                _addIngredientState.update {
                    it.copy(
                        selectedIngredientId = event.ingredient.id,
                        unit = event.ingredient.unit.toString()
                    )
                }
            }
        }
    }
}

