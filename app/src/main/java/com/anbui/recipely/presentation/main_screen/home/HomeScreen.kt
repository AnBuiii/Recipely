package com.anbui.recipely.presentation.main_screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anbui.recipely.presentation.main_screen.home.components.CategorySection
import com.anbui.recipely.presentation.main_screen.home.components.FeaturedSection
import com.anbui.recipely.presentation.main_screen.home.components.HeadingSection
import com.anbui.recipely.presentation.main_screen.home.components.PopularRecipeSection
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val name = "An Bùi"
    val selectedCategories = remember { mutableStateListOf<String>() }
    val popularRecipes by homeScreenViewModel.popularRecipe.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SpaceHuge)
    ) {
        HeadingSection(
            name = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge),
            onCartClick = {
                navController.navigate(Screen.CartScreen.route) {
                    launchSingleTop = true
                }
            }
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        FeaturedSection()

        Spacer(modifier = Modifier.height(SpaceLarge))

        CategorySection(
            selectedCategories = selectedCategories,
            onCategoryClick = { category, value ->
                if (value) {
                    selectedCategories.add(category)
                } else {
                    selectedCategories.remove(category)
                }
            }
        )

        Spacer(modifier = Modifier.height(SpaceLarge))

        PopularRecipeSection(
            popularRecipes = popularRecipes,
            onRecipeLikeClick = { id ->
            },
            onRecipeClick = { id ->
                navController.navigate(
                    Screen.RecipeDetailScreen.route
                )
            }
        )
    }
}
