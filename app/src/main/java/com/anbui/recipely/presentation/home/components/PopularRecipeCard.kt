package com.anbui.recipely.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@Composable
fun PopularRecipeCard(
    recipe: Recipe,
    onLikeClick: () -> Unit = {}
) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .size(height = 240.dp, width = 200.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = TrueWhite,
                ambientColor = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.large
            ),
        colors = CardDefaults.cardColors(containerColor = TrueWhite),
    ) {
        Column(
            modifier = Modifier
                .padding(SpaceMedium)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.large)
                    .fillMaxWidth()
                    .height(128.dp)
            ) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.description,
                    contentScale = ContentScale.Crop
                )
                FilledIconButton(
                    onClick = onLikeClick,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .padding(SpaceSmall)
                        .align(Alignment.TopEnd)
                        .size(32.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = TrueWhite,
                        contentColor = if (recipe.isLike) MaterialTheme.colorScheme.secondary
                        else MaterialTheme.colorScheme.primary
                    ),
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (recipe.isLike) R.drawable.ic_heart_filled
                            else R.drawable.ic_heart
                        ),
                        contentDescription = stringResource(R.string.heart)
                    )
                }
            }
            Text(text = recipe.title, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceTiny),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calories),
                    contentDescription = stringResource(
                        R.string.calories
                    ),
                    tint = MediumGrey
                )
                Text(
                    text = "${recipe.totalCalories} Kcal",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = MediumGrey
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_separator),
                    contentDescription = stringResource(R.string.seperator),
                    tint = MediumGrey
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = stringResource(
                        id = R.string.clock
                    ),
                    tint = MediumGrey
                )
                Text(
                    text = recipe.cookTime, style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = MediumGrey
                    )
                )
            }
        }
    }
}