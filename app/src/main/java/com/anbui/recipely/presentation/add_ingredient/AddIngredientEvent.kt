package com.anbui.recipely.presentation.add_ingredient

sealed class AddIngredientEvent{
    data class EnterIngredientName(val value: String): AddIngredientEvent()
    data class EnterAmount(val value: String): AddIngredientEvent()
    data class EnterUnit(val value: String): AddIngredientEvent()
}