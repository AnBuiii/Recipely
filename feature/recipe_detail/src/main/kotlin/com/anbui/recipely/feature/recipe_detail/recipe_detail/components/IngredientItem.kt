package com.anbui.recipely.feature.recipe_detail.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.core.designsystem.components.StandardCard
import com.anbui.recipely.core.designsystem.theme.SpaceMedium
import com.anbui.recipely.core.designsystem.theme.TrueWhite
import com.anbui.recipely.feature.recipe_detail.R
import com.anbui.recipely.feature.recipe_detail.vulgarFraction

@Composable
fun IngredientItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    amount: Float,
    unit: String,
    containerColor: Color = TrueWhite,
    textColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {},
) {
    StandardCard(
        modifier = modifier,
        containerColor = containerColor,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = name,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop,
                    fallback = painterResource(id = R.drawable.ic_fat)
                )
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(color = textColor),
                    softWrap = true
                )
            }

            Text(
                text = "${amount.vulgarFraction.first} $unit",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
            )
        }
    }
}
