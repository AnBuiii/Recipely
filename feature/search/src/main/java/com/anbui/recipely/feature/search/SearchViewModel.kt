@file:OptIn(FlowPreview::class)

package com.anbui.recipely.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val ingredientParameter = savedStateHandle.get<String?>("ingredientName")

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    private val _searchText = MutableStateFlow(ingredientParameter?.trim() ?: "")
    val searchText = _searchText.asStateFlow()

    private val _searchMode =
        MutableStateFlow(if (ingredientParameter?.isBlank() == true) SearchMode.Recipe else SearchMode.Ingredient)

    val searchMode = _searchMode.asStateFlow()

    val recipes = _searchText
        .debounce(300)
        .onEach { _state.update { it.copy(isSearching = true) } }
        .combine(_searchMode) { text, mode ->
            if (mode == SearchMode.Recipe) {
                recipeRepository.searchRecipes(text)
            } else {
                recipeRepository.searchRecipesByIngredient(text)
            }

        }
        .onEach { _state.update { it.copy(isSearching = false) } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    val recentRecipes = recipeRepository.getAllRecentOfCurrentAccount()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun asd() {
        viewModelScope.launch {
            recipeRepository.searchRecipesByIngredient(_searchText.value)
        }
    }

    fun changeSearchText(value: String) {
        _searchText.update { value }
    }

    fun onRecipeClick(recipeId: String) {
        viewModelScope.launch {
            recipeRepository.addRecentRecipe(recipeId)
        }
    }

    fun onChangeSearchMode() {
        _searchMode.update {
            if (it == SearchMode.Recipe) SearchMode.Ingredient
            else SearchMode.Recipe
        }
    }

}
