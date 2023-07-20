package com.anbui.recipely.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.TrueWhite

@Composable
fun RecipelyLargeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .size(height = 172.dp, width = 264.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        shape = MaterialTheme.shapes.large
//            .then(modifier)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_featured_bg),
                contentDescription = stringResource(
                    R.string.featured_background
                ),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.description,
                modifier = Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .offset(y = -SpaceLarge, x = SpaceLarge)
                    .size(152.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(SpaceMedium),
                verticalArrangement = Arrangement.spacedBy(SpaceTiny)
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.bodyLarge.copy(color = TrueWhite)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = recipe.ownerAvatarUrl,
                            contentDescription = recipe.ownerName,
                            modifier = Modifier
                                .size(16.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(
                            text = recipe.ownerName,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = TrueWhite
                            )
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_time),
                            contentDescription = stringResource(
                                R.string.clock
                            ),
                            tint = TrueWhite
                        )
                        Text(
                            text = recipe.cookTime,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                                color = TrueWhite
                            )
                        )
                    }

                }
            }
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun RecipelyVerticallyCard(
    recipe: Recipe,
    onLikeClick: () -> Unit = {}
) {

    StandardCard(
        modifier = Modifier.size(
            width = 200.dp,
            height = 240.dp
        )
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

@ExperimentalMaterial3Api
@Composable
fun RecipelyHorizontallyCard(
    recipe: Recipe,
    modifier: Modifier = Modifier
) {
    StandardCard(
        modifier = modifier.height(100.dp),
        contentPadding = SpaceSmall
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop

            )
            Column(
                modifier = Modifier.width(184.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = recipe.ownerAvatarUrl,
                        contentDescription = recipe.ownerName,
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                    Text(
                        text = recipe.ownerName,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MediumGrey
                        )
                    )
                }
            }
            FilledIconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = TrueWhite
                ),
                modifier = Modifier.padding(end = SpaceTiny).size(32.dp),
                shape = MaterialTheme.shapes.medium

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = stringResource(
                        R.string.arrow_right
                    ),
                )
            }

        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun RecipelyTinyCard(
    recipe: Recipe
) {
    StandardCard(
        modifier = Modifier.size(width = 100.dp, height = 136.dp),
        contentPadding = SpaceSmall
    ) {
        AsyncImage(
            model = recipe.imageUrl,
            contentDescription = recipe.title,
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .size(84.dp)
        )
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun RecipelyLargeCardPreview() {
    RecipelyLargeCard(recipe = exampleRecipes.first())
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RecipelyVerticallyCardPreview() {
    RecipelyVerticallyCard(recipe = exampleRecipes.first())
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun RecipelyTinyCardPreview() {
    RecipelyTinyCard(recipe = exampleRecipes.first())
}
