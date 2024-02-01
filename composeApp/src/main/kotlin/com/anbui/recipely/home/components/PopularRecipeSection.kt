package com.anbui.recipely.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.core.designsystem.components.RecipelyVerticallyCard
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.model.Recipe

@Composable
fun PopularRecipeSection(
    popularRecipes: List<Recipe> = emptyList(),
    onRecipeLikeClick: (Recipe, Boolean) -> Unit,
    onRecipeClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
        ) {
            Text(
                text = stringResource(R.string.popular_recipe),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.see_all),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                ),
                modifier = Modifier.padding(start = SpaceLarge)
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = SpaceLarge, vertical = SpaceMedium),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium)
        ) {
            items(popularRecipes, key = { it.id }) {
                RecipelyVerticallyCard(
                    recipe = it,
                    onLikeClick = { onRecipeLikeClick(it, !it.isLike) },
                    onClick = { onRecipeClick(it.id) }
                )
            }
        }
    }
}
