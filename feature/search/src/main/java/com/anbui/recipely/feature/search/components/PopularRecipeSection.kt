package com.anbui.recipely.feature.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.core.designsystem.components.RecipelyHorizontallyCard
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.search.R

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.matchSearchSection(
    popularRecipes: List<Recipe>,
    onRecipeClick: (String) -> Unit,
) {
    item(
        key = "Matched recipes bar"
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
        ) {
            Text(
                text = "Matched recipes",
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
    }
    items(popularRecipes, key = { "match ${it.id}" }) {
        RecipelyHorizontallyCard(
            recipe = it,
            modifier = Modifier.padding(horizontal = SpaceLarge, vertical = SpaceSmall + SpaceTiny),
            onClick = { onRecipeClick(it.id) }
        )
    }
}
