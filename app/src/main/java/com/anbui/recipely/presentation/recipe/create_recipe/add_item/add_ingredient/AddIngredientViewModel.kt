package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_ingredient

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
                _state.update {
                    AddIngredientState(
                        selectedIngredientId = ingredient.id,
                        amount = amount.toString(),
                        isEdit = true
                    )
                }
                onChangeIngredientName(ingredient.name)
                onChangeUnit(ingredient.unit.toString())
            }
        }
    }

    private val _state = MutableStateFlow(
        AddIngredientState(

        )
    )
    val state = _state.asStateFlow()

    private val _unit = MutableStateFlow("")
    val unit = _unit.asStateFlow()

    private val _ingredientName = MutableStateFlow("")
    val ingredientName = _ingredientName.asStateFlow()

    val ingredients = _ingredientName
        .debounce(300)
        .onEach { _state.update { it.copy(isSearching = true) } }
        .map {
//            emit(
            recipeRepository.searchIngredients(it)
//            )
        }
        .onEach { _state.update { it.copy(isSearching = false) } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    private fun onChangeIngredientName(newValue: String) {
        _ingredientName.update { newValue }
    }

    private fun onChangeAmount(newValue: String) {
        _state.update { it.copy(amount = newValue) }
    }

    private fun onChangeUnit(newValue: String) {
        _unit.update { newValue }
    }

    fun onEvent(event: AddIngredientEvent) {
        when (event) {
            is AddIngredientEvent.EnterIngredientName -> {
                onChangeIngredientName(event.value)
            }

            is AddIngredientEvent.EnterAmount -> {
                onChangeAmount(event.value)
            }

            is AddIngredientEvent.EnterUnit -> {
                onChangeUnit(event.value)
            }

            is AddIngredientEvent.AddIngredient -> {
                with(_state.value) {
                    if (selectedIngredientId.isNotEmpty()) {
                        try {
                            amount.toDouble()
                            _state.update { it.copy(success = true) }
                        } catch (_: Exception) {
                            _state.update { it.copy(error = "Wrong amount") }
                            _state.update { it.copy(error = null) }
                            return
                        }
                    } else {
                        Log.d("asd", "")
                    }
                }
            }

            is AddIngredientEvent.ChooseIngredient -> {
                onChangeIngredientName(event.ingredient.name)
                _state.update { it.copy(selectedIngredientId = event.ingredient.id) }
                onChangeUnit(event.ingredient.unit.toString())
            }

            is AddIngredientEvent.ChooseUnit -> {
                onChangeUnit(event.unit.unitString)
            }
        }
    }
}
