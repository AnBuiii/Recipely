package com.anbui.recipely.feature.search

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
import com.anbui.recipely.core.designsystem.components.StandardToolbar
import com.anbui.recipely.core.designsystem.theme.SpaceLarge
import com.anbui.recipely.feature.search.components.matchSearchSection
import com.anbui.recipely.feature.search.components.recentSearchSection
import com.anbui.recipely.feature.search.components.searchBarSection

@Composable
fun SearchRoute(
    onBack: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    SearchScreen(
        onBack = onBack,
        onNavigateToRecipe = onNavigateToRecipe,
        viewModel = searchViewModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onNavigateToRecipe: (String) -> Unit,
    viewModel: SearchViewModel
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val searchRecipes by viewModel.recipes.collectAsStateWithLifecycle()
    val recentSearch by viewModel.recentRecipes.collectAsStateWithLifecycle()
    val searchMode by viewModel.searchMode.collectAsStateWithLifecycle()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 64.dp)
    ) {
        item(
            key = "tool bar"
        ) {
            StandardToolbar(
                title = stringResource(R.string.search),
                onBack = onBack
            )
        }

        searchBarSection(
            searchText = searchText,
            onSearchTextChange = viewModel::changeSearchText,
            modifier = Modifier.padding(
                start = SpaceLarge,
                end = SpaceLarge,
                bottom = SpaceLarge
            ),
            searchMode = searchMode,
            onChangeMode = viewModel::onChangeSearchMode
        )

        recentSearchSection(recentSearches = recentSearch)

        matchSearchSection(
            popularRecipes = searchRecipes,
            onRecipeClick = {
                viewModel.onRecipeClick(it)
                onNavigateToRecipe(it)
            }
        )
    }
}
