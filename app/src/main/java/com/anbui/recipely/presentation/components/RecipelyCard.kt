package com.anbui.recipely.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
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
import com.anbui.recipely.domain.models.Notification
import com.anbui.recipely.domain.models.NotificationType
import com.anbui.recipely.domain.models.Recipe
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.ui.theme.GoogleRed
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
                    contentDescription = stringResource(R.string.heart),
                    tint = Color.Unspecified
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
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(100f / 84f)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop

            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    softWrap = true
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
                        ),
                    )
                }
            }
            FilledIconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = TrueWhite
                ),
                modifier = Modifier
                    .padding(end = SpaceTiny)
                    .size(32.dp),
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
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = recipe.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun RecipelyNotificationCard(
    notification: Notification,
    modifier: Modifier = Modifier
) {
    val titleText = when (notification.notificationType) {
        NotificationType.CommentedOnRecipe, NotificationType.LikedRecipe -> {
            stringResource(R.string.recipe_interaction)
        }

        NotificationType.FollowedUser -> {
            stringResource(R.string.about_you)
        }

        NotificationType.Promo -> {
            stringResource(R.string.promo)
        }

        NotificationType.RecipeRecommendation -> {
            stringResource(R.string.recipe_recommendation)
        }

        NotificationType.Order -> {
            stringResource(R.string.order)
        }
    }

    val icon = when (notification.notificationType) {
        NotificationType.CommentedOnRecipe, NotificationType.LikedRecipe -> {
            null
        }

        NotificationType.FollowedUser -> {
            painterResource(id = R.drawable.ic_profile_filled)
        }

        NotificationType.Promo -> {
            painterResource(id = R.drawable.ic_discount)
        }

        NotificationType.RecipeRecommendation -> {
            painterResource(id = R.drawable.ic_discount)
        }

        NotificationType.Order -> {
            painterResource(id = R.drawable.ic_bag)
        }
    }


    StandardCard(
        modifier = modifier.height(80.dp),
        contentPadding = SpaceMedium
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(SpaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (notification.imageUrl != null) {
                AsyncImage(
                    model = notification.imageUrl,
                    contentDescription = stringResource(id = R.string.notification),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop

                )
            } else {
                FilledIconButton(
                    onClick = { },
                    enabled = false,
                    shape = MaterialTheme.shapes.large,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContentColor = MaterialTheme.colorScheme.secondary
                    ),
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                ) {
                    if (icon != null) {
                        Icon(
                            painter = icon,
                            contentDescription = stringResource(id = R.string.notification),
                            tint = Color.Unspecified

                        )
                    }
                }
            }


            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = titleText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MediumGrey
                        ),
                        maxLines = 1,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = notification.formattedTime,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Normal),
                        maxLines = 1,
                        softWrap = true
                    )

                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpaceLarge),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = notification.message,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = true,
                        modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(GoogleRed)
                    )
                }


            }


        }

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
