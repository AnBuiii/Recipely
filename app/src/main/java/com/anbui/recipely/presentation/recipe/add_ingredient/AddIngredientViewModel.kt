package com.anbui.recipely.presentation.recipe.add_ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.models.UnitType
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    recipeRepository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddIngredientState())
    val state = _state.asStateFlow()

    private val _unit = MutableStateFlow("")
    val unit = _unit.asStateFlow()

    val units = _unit
        .debounce(300)
        .onEach { _state.update { it.copy(isSearching = true) } }
        .transform {
            emit(UnitType.indices().filter { unit -> unit.toString().contains(it) }.take(4))
        }
        .onEach { _state.update { it.copy(isSearching = false) } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    private val _ingredientName = MutableStateFlow("")
    val ingredientName = _ingredientName.asStateFlow()

    val ingredients = _ingredientName
        .debounce(300)
        .onEach { _state.update { it.copy(isSearching = true) } }
        .transform {
            emit(recipeRepository.searchIngredients(it))
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

            }
            is AddIngredientEvent.ChooseIngredient -> {
                onChangeIngredientName(event.ingredient.name)
            }
            is AddIngredientEvent.ChooseUnit -> {
                onChangeUnit(event.unit.unitString)
            }
        }
    }
}
