package com.anbui.recipely.presentation.main_screen.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.presentation.main_screen.search.components.matchSearchSection
import com.anbui.recipely.presentation.main_screen.search.components.recentSearchSection
import com.anbui.recipely.presentation.main_screen.search.components.searchBarSection
import com.anbui.recipely.presentation.ui.components.StandardToolbar
import com.anbui.recipely.presentation.ui.theme.SpaceLarge
import com.anbui.recipely.presentation.util.Screen

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
    val searchMode by searchViewModel.searchMode.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 64.dp)
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
            ),
            searchMode = searchMode,
            onChangeMode = searchViewModel::onChangeSearchMode
        )

        recentSearchSection(recentSearches = recentSearch)

        matchSearchSection(
            popularRecipes = searchRecipes,
            onRecipeClick = {
                searchViewModel.onRecipeClick(it)
                navController.navigate(
                    Screen.RecipeDetailScreen.route + "/$it"
                )
            }
        )
    }
}
