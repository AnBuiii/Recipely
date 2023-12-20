package com.anbui.recipely.feature.recipe_detail.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.theme.DarkGrey
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.feature.recipe_detail.R

fun LazyListScope.creatorSection(
    recipe: Recipe
) {
    item {
        Spacer(modifier = Modifier.height(SpaceLarge))

        Divider(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f))

        Spacer(modifier = Modifier.height(SpaceMedium))
    }
    item {
        Text(
            text = stringResource(R.string.creator),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(SpaceMedium))

        Row(
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.ownerAvatarUrl,
                contentDescription = recipe.ownerName,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column {
                Text(
                    text = recipe.ownerName,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                )
                Text(
                    text = recipe.ownerDescription,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = DarkGrey
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(SpaceLarge))
    }
}
