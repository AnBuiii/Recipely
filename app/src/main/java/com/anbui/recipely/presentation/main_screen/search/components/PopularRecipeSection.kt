package com.anbui.recipely.presentation.main_screen.search.components

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
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.presentation.ui.components.RecipelyHorizontallyCard
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny

@ExperimentalMaterial3Api
fun LazyListScope.popularRecipeSection(
    popularRecipes: List<Recipe>,
    onRecipeClick: (String) -> Unit,
) {
    item(
        key = "popular recipes bar"
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
        ) {
            Text(
                text = stringResource(R.string.popular_recipes),
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
    items(popularRecipes, key = { "popular ${it.id}" }) {
        RecipelyHorizontallyCard(
            recipe = it,
            modifier = Modifier.padding(horizontal = SpaceLarge, vertical = SpaceSmall + SpaceTiny),
            onClick = {onRecipeClick(it.id)}
        )
    }
}
