package com.anbui.recipely.feature.recipe_detail.cooking_detail.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.components.StandardCard
import com.anbui.recipely.core.designsystem.theme.DarkGrey
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceSmall
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.recipe_detail.R

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.instructionsSection(
    recipe: Recipe,
) {
    item(
        key = "instruction section title"
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .animateItemPlacement()
        ) {
            Text(
                text = stringResource(R.string.instructor),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.steps, recipe.instructions.size),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Normal,
                    color = DarkGrey
                )
            )
        }
    }

    items(
        recipe.instructions.sortedBy { it.order },
        key = {
            "Step ${it.order}"
        }
    ) {
        StandardCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = SpaceMedium)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.size(28.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${it.order}",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier
                        )
                    }
                }
                Text(
                    text = it.instruction,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = DarkGrey
                    )
                )
            }
        }
    }
}
