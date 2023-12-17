package com.anbui.recipely.feature.search

data class SearchScreenState(
    val searchText: String = "",
    val searchedTexts: List<String> = listOf(),
    val isSearchFocused: Boolean = false,
    val isSearching: Boolean = false,
)

enum class SearchMode {
    Recipe, Ingredient
}

