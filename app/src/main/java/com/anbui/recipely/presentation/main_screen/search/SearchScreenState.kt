package com.anbui.recipely.presentation.main_screen.search

data class SearchScreenState(
    val searchText: String = "",
    val searchedTexts: List<String> = listOf(),
    val isSearchFocused: Boolean = false,
    val isSearching: Boolean = false,
)

