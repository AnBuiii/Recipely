package com.anbui.recipely.presentation.recipe.recipe_detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anbui.recipely.domain.models.exampleIngredientItems
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.recipe.recipe_detail.components.HeadingSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.creatorSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.descriptionSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.ingredientsSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.instructionsSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.overviewSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.relatedRecipesSection
import com.anbui.recipely.presentation.recipe.recipe_detail.components.viewModeSection
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.util.Screen

@ExperimentalFoundationApi
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
            HeadingSection(recipe = recipe)

            LazyColumn(
                modifier = Modifier.padding(horizontal = SpaceLarge)
            ) {

                descriptionSection(
                    description = recipe.description,
                    isExpanded = recipeDetailViewModel.isDescriptionExpanded.value,
                    onHintClick = { recipeDetailViewModel.changeDescriptionExpandedState() }
                )

                overviewSection(
                    recipe = recipe
                )

                // view mode section
                viewModeSection(
                    viewMode = recipeDetailViewModel.viewMode.value,
                    onChangeViewMode = recipeDetailViewModel::changeViewMode
                )

                if (recipeDetailViewModel.viewMode.value is ViewMode.Ingredients) {
                    ingredientsSection(
                        recipe = recipe,
                        servings = recipeDetailViewModel.servings.value,
                        onChangeServing = recipeDetailViewModel::changeServings
                    )
                } else {
                    instructionsSection(
                        recipe = recipe,
                        onStartCookingClick = {
                            navController.navigate(Screen.CookingDetailScreen.route)
                        }
                    )
                }

                creatorSection(recipe = recipe)

                // related recipe section

                relatedRecipesSection(recipes = exampleRecipes)
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

    LaunchedEffect(Unit) {
        recipeDetailViewModel.changeServings(recipe.servings)
    }
}



