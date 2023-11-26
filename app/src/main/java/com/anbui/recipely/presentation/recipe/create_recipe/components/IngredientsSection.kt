package com.anbui.recipely.presentation.recipe.create_recipe.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.presentation.recipe.create_recipe.CreateRecipeEvent

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun IngredientsSection(
    onEvent: (CreateRecipeEvent) -> Unit,
    ingredients: List<IngredientItem>,
    onAddIngredientClick: () -> Unit,
    onEditIngredient: (String, Float) -> Unit
) {
    Column {
        IngredientDragDropList(
            items = ingredients,
            onMove = { from, to -> onEvent(CreateRecipeEvent.SwapIngredient(from, to)) },
            onAddIngredientClick = onAddIngredientClick,
            onEditIngredient = onEditIngredient
        )
    }
}
