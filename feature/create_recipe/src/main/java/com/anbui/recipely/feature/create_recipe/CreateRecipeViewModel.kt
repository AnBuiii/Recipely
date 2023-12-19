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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
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
                val newList = _state.value.coverImages.mapIndexed { idx, uri ->
                    if (idx != event.index) uri
                    else event.value
                }
                _state.update { it.copy(coverImages = newList) }
            }

            is CreateRecipeEvent.RemoveImage -> {
                val newList = _state.value.coverImages.filterIndexed { idx, _ ->
                    idx != event.index
                }
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
                    with(_state.value) {
                        val index =
                            ingredientItems.indexOfFirst { it.ingredientId == event.ingredientItem.ingredientId }

                        if (index != -1) {
                            val newList = ingredientItems.mapIndexed { idx, item ->
                                if (idx != index) item
                                else event.ingredientItem
                            }
                            _state.update { it.copy(ingredientItems = newList) }
                        } else {
                            _state.update { it.copy(ingredientItems = it.ingredientItems + event.ingredientItem) }
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
                        val newList = _state.value.steps.mapIndexed { idx, step ->
                            if (idx != index) step
                            else step.copy(
                                period = event.period.toLong(),
                                instruction = event.instruction
                            )
                        }
                        _state.update { it.copy(steps = newList) }
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

    private var searchJob: Job? = null

    private val _addIngredientState = MutableStateFlow(AddIngredientState())
    val addIngredientState = _addIngredientState.asStateFlow()

    private suspend fun searchIngredient() {
        _addIngredientState
            .map { it.name }
            .distinctUntilChanged()
            .debounce(300)
            .onEach { _addIngredientState.update { it.copy(isSearching = true) } }
            .map(recipeRepository::searchIngredients)
            .onEach { _addIngredientState.update { it.copy(isSearching = false) } }
            .collect { list ->
                Log.d("ingredient", list.toString())
                _addIngredientState.update { it.copy(ingredients = list) }
            }
    }

    fun onAddIngredientEvent(event: AddIngredientEvent) {
        when (event) {
            is AddIngredientEvent.EnterIngredientName -> {
                _addIngredientState.update { it.copy(name = event.value) }
            }

            is AddIngredientEvent.EnterAmount -> {
                _addIngredientState.update { it.copy(amount = event.value) }
            }

            is AddIngredientEvent.AddIngredient -> {
                with(_addIngredientState.value) {
                    if (selectedIngredientId.isNotEmpty()) {
                        try {
                            _addIngredientState.value.ingredients.find {
                                it.id == selectedIngredientId
                            }?.let {
                                onEvent(CreateRecipeEvent.AddIngredient(it.toIngredientItem(amount.toFloat())))
                            }
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
                _addIngredientState.update { it.copy(name = event.ingredient.name) }
                _addIngredientState.update {
                    it.copy(
                        selectedIngredientId = event.ingredient.id,
                        unit = event.ingredient.unit.toString()
                    )
                }
            }

            is AddIngredientEvent.Dispose -> {
                _addIngredientState.update { AddIngredientState() }
                searchJob?.cancel()
            }

            is AddIngredientEvent.Init -> {
                if (searchJob?.isActive != true) {
                    searchJob = viewModelScope.launch {
                        searchIngredient()
                    }
                }
                event.ingredientItem?.let { item ->
                    _addIngredientState.update {
                        it.copy(
                            name = item.name,
                            selectedIngredientId = item.ingredientId,
                            amount = item.amount.toString(),
                            unit = item.unit.unitString,
                            isEdit = true
                        )
                    }
                }
            }
        }
    }
}

