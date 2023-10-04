package com.anbui.recipely.presentation.recipe.add_ingredient

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anbui.recipely.domain.models.exampleIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(

) : ViewModel() {
    private val _ingredientName = mutableStateOf<String>("")
    val ingredientName: State<String> = _ingredientName

    private fun onChangeIngredientName(newValue: String) {
        _ingredientName.value = newValue
    }

    private val _amount = mutableStateOf<String>("")
    val amount: State<String> = _amount

    private fun onChangeAmount(newValue: String) {
        _amount.value = newValue
    }

    private val _unit = mutableStateOf<String>("")
    val unit: State<String> = _unit

    private fun onChangeUnit(newValue: String) {
        _unit.value = newValue
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
        }
    }

    val searchResult = exampleIngredients

}

