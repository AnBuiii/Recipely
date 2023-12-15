package com.anbui.recipely.presentation.main_screen.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.R
import com.anbui.model.Recipe
import com.anbui.recipely.presentation.ui.components.RecipelyTinyCard
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium

@ExperimentalMaterial3Api
fun LazyListScope.recentSearchSection(
    recentSearches: List<Recipe>,
    modifier: Modifier = Modifier
) {
    item(
        key = "recent search bar"
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge)
        ) {
            Text(
                text = stringResource(R.string.recent_search),
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

    item {
        LazyRow(
            contentPadding = PaddingValues(horizontal = SpaceLarge, vertical = SpaceMedium),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium)
        ) {
            items(recentSearches, key = { it.id }) {
                RecipelyTinyCard(recipe = it)
            }
        }
    }
}
