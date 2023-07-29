package com.anbui.recipely.presentation.cooking_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.exampleIngredientItems
import com.anbui.recipely.presentation.recipe_detail.components.IngredientItem
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

fun LazyListScope.ingredientsSection(
    recipe: Recipe,
    servings: Int,
) {
    item {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = stringResource(
                    R.string.items,
                    exampleIngredientItems.size
                ),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
            )
        }
    }
    items(recipe.ingredients, key = { it.ingredientId }) {
        IngredientItem(
            imageUrl = it.imageUrl,
            name = it.name,
            amount = it.amount * servings / recipe.servings,
            unit = it.unit.toString(),
            modifier = Modifier.padding(vertical = SpaceMedium)
        )
    }
}