package com.anbui.recipely.feature.recipe_detail.recipe_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.SpaceTiny
import com.anbui.recipely.core.designsystem.theme.ThinGrey
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.recipe_detail.ViewMode

@ExperimentalMaterial3Api
fun LazyListScope.viewModeSection(
    viewMode: ViewMode,
    onChangeViewMode: (ViewMode) -> Unit
) {
    item {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = CardDefaults.cardColors(
                containerColor = ThinGrey
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceTiny)
                    .clip(MaterialTheme.shapes.large),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Card(
                    modifier = Modifier
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = if (viewMode is ViewMode.Ingredients) MaterialTheme.colorScheme.primary else ThinGrey
                    ),
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        onChangeViewMode(ViewMode.Ingredients)
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Ingredients",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (viewMode is ViewMode.Ingredients) TrueWhite else MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = if (viewMode is ViewMode.Instructions) MaterialTheme.colorScheme.primary else ThinGrey
                    ),
                    shape = MaterialTheme.shapes.large,
                    onClick = {
                        onChangeViewMode(ViewMode.Instructions)
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Instructions",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = if (viewMode is ViewMode.Instructions) TrueWhite else MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(SpaceMedium))
    }
}
