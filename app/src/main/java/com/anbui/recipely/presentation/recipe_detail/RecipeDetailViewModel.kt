package com.anbui.recipely.presentation.recipe_detail

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalMaterial3Api
@HiltViewModel
class RecipeDetailViewModel @Inject constructor() : ViewModel() {

    private val _bottomSheetState = mutableStateOf(
        SheetState(
            skipPartiallyExpanded = false,
            skipHiddenState = true,
            initialValue = SheetValue.PartiallyExpanded
        )
    )
    val bottomSheetState: State<SheetState> = _bottomSheetState

    private val _bottomSheetScaffoldState = mutableStateOf(
        BottomSheetScaffoldState(
            bottomSheetState = bottomSheetState.value,
            snackbarHostState = SnackbarHostState()
        )
    )

    val bottomSheetScaffoldState: State<BottomSheetScaffoldState> = _bottomSheetScaffoldState

    private val _viewMode = mutableStateOf<ViewMode>(ViewMode.Ingredients)
    val viewMode: State<ViewMode> = _viewMode

    fun changeViewMode(newValue: ViewMode) {
        _viewMode.value = newValue
    }

    private val _servings = mutableStateOf(1)
    val servings: State<Int> = _servings

    fun changeServings(newValue: Int) {
        _servings.value = newValue
    }

    private val _isDescriptionExpanded = mutableStateOf(false)
    val isDescriptionExpanded: State<Boolean> = _isDescriptionExpanded

    fun changeDescriptionExpandedState() {
        _isDescriptionExpanded.value = !_isDescriptionExpanded.value
    }

}

sealed class ViewMode {
    data object  Ingredients : ViewMode()
    data object Instructions : ViewMode()
}
