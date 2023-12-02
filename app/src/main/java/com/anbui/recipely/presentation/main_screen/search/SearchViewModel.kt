@file:OptIn(FlowPreview::class)

package com.anbui.recipely.presentation.main_screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val recipes = _searchText
        .debounce(300)
        .onEach { _state.update { it.copy(isSearching = true) } }
        .map {
            recipeRepository.searchRecipes(it)
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

    fun changeSearchText(value: String) {
        _searchText.update { value }
    }

    fun onRecipeClick(recipeId: String) {
        viewModelScope.launch {
            recipeRepository.addRecentRecipe(recipeId)
        }
    }

}
