package com.anbui.recipely.presentation.recipe_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.presentation.components.StandardCard
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.util.vulgarFraction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientItem(
    imageUrl: String?,
    name: String,
    amount: Float,
    unit: String,
    modifier: Modifier = Modifier
) {
    StandardCard(
        modifier = modifier,
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
                    style = MaterialTheme.typography.bodyLarge,
                    softWrap = true,
                )
            }

            Text(
                text = "${amount.vulgarFraction.first} $unit",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}