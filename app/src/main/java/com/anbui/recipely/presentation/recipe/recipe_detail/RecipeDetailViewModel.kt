package com.anbui.recipely.presentation.recipe.recipe_detail

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalMaterial3Api
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])
    val recipe = recipeRepository.getRecipesById(recipeId)
        .onEach {
            changeServings(it.servings)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            recipeRepository.getDummyRecipe()
        )

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

    private val _servings = mutableIntStateOf(1)
    val servings: State<Int> = _servings

    fun changeServings(newValue: Int) {
        _servings.value = newValue
    }

    private val _isDescriptionExpanded = mutableStateOf(false)
    val isDescriptionExpanded: State<Boolean> = _isDescriptionExpanded

    fun changeDescriptionExpandedState() {
        _isDescriptionExpanded.value = !_isDescriptionExpanded.value
    }

    fun likeRecipe() {
        viewModelScope.launch {
            recipeRepository.likeRecipe(recipeId, !recipe.first().isLike)
        }
    }

    fun addAllIngredientToCart(){
        viewModelScope.launch {

        }
    }
}

sealed class ViewMode {
    data object Ingredients : ViewMode()
    data object Instructions : ViewMode()
}
