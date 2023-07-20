package com.anbui.recipely.presentation.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.anbui.recipely.R
import com.anbui.recipely.domain.models.exampleRecipes
import com.anbui.recipely.presentation.components.StandardToolbar
import com.anbui.recipely.presentation.search.components.popularRecipeSection
import com.anbui.recipely.presentation.search.components.recentSearchSection
import com.anbui.recipely.presentation.search.components.searchBarSection
import com.anbui.recipely.presentation.ui.theme.SpaceLarge


@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavController
) {
    var searchText by remember { mutableStateOf("") }
    val recentSearch = remember {
        exampleRecipes.toMutableStateList()
    }
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
            onSearchTextChange = { searchText = it },
            modifier = Modifier.padding(
                start = SpaceLarge,
                end = SpaceLarge,
                bottom = SpaceLarge
            )
        )

        recentSearchSection(recentSearches = recentSearch)

        popularRecipeSection(popularRecipes = recentSearch)

    }

}

