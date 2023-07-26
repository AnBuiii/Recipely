package com.anbui.recipely.presentation.recipe_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.exampleIngredientItems
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.components.StandardExpandingText
import com.anbui.recipely.presentation.recipe_detail.components.IngredientItem
import com.anbui.recipely.presentation.recipe_detail.components.NutritionItem
import com.anbui.recipely.presentation.ui.theme.MediumGrey
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.ui.theme.SpaceMedium
import com.anbui.recipely.presentation.ui.theme.SpaceSmall
import com.anbui.recipely.presentation.ui.theme.SpaceTiny
import com.anbui.recipely.presentation.ui.theme.ThinGrey
import com.anbui.recipely.presentation.ui.theme.TrueWhite
import kotlin.math.roundToInt

@ExperimentalStdlibApi
@ExperimentalMaterial3Api
@Composable
fun RecipeDetailScreen(
    navController: NavController,
    recipeDetailViewModel: RecipeDetailViewModel = hiltViewModel()
) {
    val recipe = exampleRecipes[0]
    val configuration = LocalConfiguration.current
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = recipeDetailViewModel.bottomSheetScaffoldState.value,
        sheetPeekHeight = configuration.screenHeightDp.dp * 2 / 3,
        sheetContainerColor = MaterialTheme.colorScheme.background,
        sheetShadowElevation = 16.dp,
        sheetContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = SpaceLarge),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recipe.title,
                    softWrap = true,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )
//                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.offset(y = (12).dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = recipe.id,
                        tint = MediumGrey
                    )
                    Text(
                        text = recipe.cookTime,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.Normal,
                            color = MediumGrey
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.padding(top = SpaceSmall))
            LazyColumn(
                modifier = Modifier.padding(horizontal = SpaceLarge)
            ) {
                item {
                    Row {
                        StandardExpandingText(
                            longText = recipe.description,
                            isExpanded = recipeDetailViewModel.isDescriptionExpanded.value,
                            onHintClick = { recipeDetailViewModel.changeDescriptionExpandedState() })
                    }
                    Spacer(modifier = Modifier.height(SpaceMedium))

                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        NutritionItem(
                            icon = R.drawable.ic_carb,
                            text = stringResource(R.string.carbs, recipe.totalCarb.roundToInt()),
                            modifier = Modifier.weight(1f)
                        )
                        NutritionItem(
                            icon = R.drawable.ic_protein,
                            text = stringResource(
                                R.string.proteins,
                                recipe.totalProtein.roundToInt()
                            ),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        NutritionItem(
                            icon = R.drawable.ic_calories,
                            text = stringResource(R.string.kcal, recipe.totalCalories.roundToInt()),
                            modifier = Modifier.weight(1f)

                        )
                        NutritionItem(
                            icon = R.drawable.ic_fat,
                            text = stringResource(R.string.fats, recipe.totalFat.roundToInt()),
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = ThinGrey,
                        ),
                        shape = MaterialTheme.shapes.large,
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
                                    containerColor = if (recipeDetailViewModel.viewMode.value == ViewMode.Ingredients) MaterialTheme.colorScheme.primary else ThinGrey
                                ),
                                shape = MaterialTheme.shapes.large,
                                onClick = {
                                    recipeDetailViewModel.changeViewMode(ViewMode.Ingredients)
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Ingredients",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = if (recipeDetailViewModel.viewMode.value == ViewMode.Ingredients) TrueWhite else MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }

                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .weight(1f),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (recipeDetailViewModel.viewMode.value == ViewMode.Instructions) MaterialTheme.colorScheme.primary else ThinGrey
                                ),
                                shape = MaterialTheme.shapes.large,
                                onClick = {
                                    recipeDetailViewModel.changeViewMode(ViewMode.Instructions)
                                }
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Instructions",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = if (recipeDetailViewModel.viewMode.value == ViewMode.Instructions) TrueWhite else MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.ingredients),
                            style = MaterialTheme.typography.titleLarge,
                        )
                       Row {
                           Text(
                               text = stringResource(R.string.ingredients),
                               style = MaterialTheme.typography.titleLarge,
                           )
                       }
                    }
                }
                items(exampleIngredientItems, key = { it.id }) {
                    IngredientItem(
                        imageUrl = it.imageUrl,
                        name = it.name,
                        amount = it.amount,
                        unit = it.unit.toString(),
                        modifier = Modifier.padding(vertical = SpaceMedium)
                    )
                }
            }

        }) { _ ->
        Box(
            Modifier
                .fillMaxWidth()
                .height(configuration.screenHeightDp.dp * 1 / 3 + 64.dp)
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.id,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}


