package com.anbui.recipely.home

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.anbui.recipely.core.designsystem.theme.SpaceHuge
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.feature.cart.navigation.onNavigateToCartGraph
import com.anbui.recipely.home.components.CategorySection
import com.anbui.recipely.home.components.FeaturedSection
import com.anbui.recipely.home.components.HeadingSection
import com.anbui.recipely.home.components.PopularRecipeSection
import com.example.ingredient_detect.permission.isGranted


@Composable
fun HomeRoute(
    navigateToRecipeDetail: (String) -> Unit,
    navigateToCart: () -> Unit,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    HomeScreen(
        navigateToRecipeDetail = navigateToRecipeDetail,
        navigateToCart = navigateToCart,
        viewModel = homeScreenViewModel
    )
}

@Composable
fun HomeScreen(
    navigateToRecipeDetail: (String) -> Unit,
    navigateToCart: () -> Unit,
    viewModel: HomeScreenViewModel
) {
    val selectedCategories = remember { mutableStateListOf<String>() }
    val popularRecipes by viewModel.popularRecipe.collectAsState()
    val currentAccount by viewModel.currentAccount.collectAsState()

    val cameraPermissionState = com.example.ingredient_detect.permission.rememberPermissionState(
        Manifest.permission.POST_NOTIFICATIONS,
    )
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = SpaceHuge)
    ) {
        HeadingSection(
            name = currentAccount.firstName + " " + currentAccount.lastName + viewModel.platform.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceLarge),
            onCartClick = navigateToCart
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
            onRecipeLikeClick = { recipe, like ->
                viewModel.likeRecipe(recipe, like)
            },
            onRecipeClick = navigateToRecipeDetail
        )
    }
}
