package com.anbui.recipely.presentation.recipe_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.IngredientItem
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.exampleIngredientItems
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.TrueWhite

fun LazyListScope.ingredientsSection(
    ingredients: List<IngredientItem>,
    recipe: Recipe,
    servings: Int,
    onChangeServing: (Int) -> Unit
) {
    item {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = stringResource(R.string.ingredients),
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = stringResource(
                        R.string.items,
                        exampleIngredientItems.size
                    ),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.width(intrinsicSize = IntrinsicSize.Max)
            ) {
                Text(
                    text = stringResource(R.string.servings),
                    style = MaterialTheme.typography.titleLarge,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedIconButton(
                        onClick = {
                            onChangeServing(
                                (servings - 1).coerceAtLeast(0)
                            )
                        },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(16.dp),
                        border = BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = stringResource(
                                R.string.negative
                            ),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "$servings",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
                    )
                    OutlinedIconButton(
                        onClick = {
                            onChangeServing(
                                servings + 1
                            )
                        },
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.size(16.dp),
                        border = BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = stringResource(
                                R.string.negative,
                            ),
                            tint = Color.Unspecified,
                        )
                    }
                }

            }
        }
    }
    items(ingredients, key = { it.id }) {
        IngredientItem(
            imageUrl = it.imageUrl,
            name = it.name,
            amount = it.amount * servings / recipe.servings,
            unit = it.unit.toString(),
            modifier = Modifier.padding(vertical = SpaceMedium)
        )
    }
    item {
        Button(
            onClick = {
            },
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)

        ) {
            Text(
                text = "Add To Cart",
                style = MaterialTheme.typography.bodyMedium.copy(TrueWhite),
                modifier = Modifier.padding(vertical = SpaceSmall)
            )
        }

        Spacer(modifier = Modifier.height(SpaceLarge))

        Divider(color = MaterialTheme.colorScheme.secondary)

        Spacer(modifier = Modifier.height(SpaceMedium))

    }
}