package com.anbui.recipely.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.home.components.CategorySection
import com.anbui.recipely.presentation.home.components.FeaturedSection
import com.anbui.recipely.presentation.home.components.HeadingSection
import com.anbui.recipely.presentation.home.components.PopularRecipeSection
import com.anbui.recipely.presentation.ui.theme.SpaceHuge
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.util.Screen

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavController
) {
    val name = "An Bùi"
    val selectedCategories = remember { mutableStateListOf<String>() }
    val popularRecipes = remember { exampleRecipes.toMutableStateList() }
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
                navController.navigate(Screen.CartScreen.route){
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
                val idx = popularRecipes.indexOfFirst { it.id == id }
                val recipe = popularRecipes[idx]
                popularRecipes[idx] = recipe.copy(isLike = !recipe.isLike)
            },
            onRecipeClick = { id ->
                navController.navigate(
                    Screen.RecipeDetailScreen.route
                )
            }
        )
    }

}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}