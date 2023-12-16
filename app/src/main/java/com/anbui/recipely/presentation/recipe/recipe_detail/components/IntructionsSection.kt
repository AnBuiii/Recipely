package com.anbui.recipely.presentation.recipe.recipe_detail.components

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.presentation.ui.components.StandardCard
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
fun LazyListScope.instructionsSection(
    recipe: Recipe,
    onStartCookingClick: () -> Unit,
    modifier: Modifier = Modifier
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
    item {
        Button(
            onClick = onStartCookingClick,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )

        ) {
            Text(
                text = stringResource(R.string.start_cooking),
                style = MaterialTheme.typography.bodyMedium.copy(TrueWhite),
                modifier = Modifier.padding(vertical = SpaceSmall)
            )
        }
    }
}
