package com.anbui.recipely.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.core.designsystem.components.RecipelyLargeCard
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.model.exampleRecipes

@Composable
fun FeaturedSection(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Text(
            stringResource(R.string.featured),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = SpaceLarge)
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = SpaceLarge, vertical = SpaceMedium),
            horizontalArrangement = Arrangement.spacedBy(SpaceLarge)
        ) {
            items(exampleRecipes) {
                RecipelyLargeCard(recipe = it, modifier = Modifier.padding(horizontal = SpaceLarge))
            }
        }
    }
}
