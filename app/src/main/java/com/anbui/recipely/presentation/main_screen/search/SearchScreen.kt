package com.anbui.recipely.presentation.main_screen.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.main_screen.search.components.popularRecipeSection
import com.anbui.recipely.presentation.main_screen.search.components.recentSearchSection
import com.anbui.recipely.presentation.main_screen.search.components.searchBarSection
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by searchViewModel.state.collectAsStateWithLifecycle()
    val searchText by searchViewModel.searchText.collectAsStateWithLifecycle()
    val searchRecipes by searchViewModel.recipes.collectAsStateWithLifecycle()
    val recentSearch by searchViewModel.recentRecipes.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item(
            key = "tool bar"
        ) {
            StandardToolbar(
                navController = navController,
                title = stringResource(id = R.string.search)
            )
        }

        searchBarSection(
            searchText = searchText,
            onSearchTextChange = searchViewModel::changeSearchText,
            modifier = Modifier.padding(
                start = SpaceLarge,
                end = SpaceLarge,
                bottom = SpaceLarge
            )
        )

        recentSearchSection(recentSearches = recentSearch)

        popularRecipeSection(
            popularRecipes = searchRecipes,
            onRecipeClick = searchViewModel::onRecipeClick
        )
    }
}
