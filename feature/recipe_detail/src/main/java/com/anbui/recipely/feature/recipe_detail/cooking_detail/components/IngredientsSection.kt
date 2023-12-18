package com.anbui.recipely.feature.recipe_detail.cooking_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.recipe_detail.R
import com.anbui.recipely.feature.recipe_detail.recipe_detail.components.IngredientItem

fun LazyListScope.ingredientsSection(
    recipe: Recipe,
    servings: Int
) {
    item {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(
                    R.string.items,
                    recipe.ingredients.size
                ),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
            )
        }
    }
    items(recipe.ingredients, key = { it.ingredientId }) {
        IngredientItem(
            modifier = Modifier.padding(vertical = SpaceMedium),
            imageUrl = it.imageUrl,
            name = it.name,
            amount = it.amount * servings / recipe.servings,
            unit = it.unit.toString()
        )
    }
}
