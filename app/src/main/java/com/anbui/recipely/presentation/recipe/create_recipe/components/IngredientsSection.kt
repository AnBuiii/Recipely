package com.anbui.recipely.presentation.recipe.create_recipe.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import com.anbui.recipely.domain.models.Ingredient
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.presentation.recipe.create_recipe.CreateRecipeEvent

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun IngredientsSection(
    onEvent: (CreateRecipeEvent) -> Unit,
    ingredients: List<IngredientItem>,
    onAddIngredientClick: () -> Unit
) {
    Column {
        IngredientDragDropList(
            items = ingredients,
            onMove = { from, to -> onEvent(CreateRecipeEvent.SwapIngredient(from, to)) },
            onAddIngredientClick = onAddIngredientClick
        )
    }
}
