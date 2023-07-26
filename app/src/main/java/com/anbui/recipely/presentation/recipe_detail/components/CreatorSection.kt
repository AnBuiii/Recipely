package com.anbui.recipely.presentation.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.presentation.ui.theme.DarkGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium

fun LazyListScope.creatorSection(
    recipe: Recipe
){
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