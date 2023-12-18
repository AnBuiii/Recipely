package com.anbui.recipely.feature.create_recipe.add_ingredient

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
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

@OptIn(FlowPreview::class)
@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        val ingredientId = savedStateHandle.get<String>("ingredientId") ?: ""
        val amount = savedStateHandle.get<Float>("amount")
        viewModelScope.launch {
            recipeRepository.getIngredientById(ingredientId)?.let { ingredient ->
                _addIngredientState.update {
                    AddIngredientState(
                        selectedIngredientId = ingredient.id,
                        amount = amount.toString(),
                        isEdit = true,
                        unit = ingredient.unit.toString()
                    )
                }
                onChangeIngredientName(ingredient.name)
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

    private fun onChangeIngredientName(newValue: String) {
        _ingredientName.update { newValue }
    }

    fun onEvent(event: AddIngredientEvent) {
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
                _addIngredientState.update { it.copy(selectedIngredientId = event.ingredient.id) }
                onChangeUnit(event.ingredient.unit.toString())
            }
        }
    }
}
