package com.anbui.recipely.feature.recipe_detail

import android.os.CountDownTimer
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.CartRepository
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val cartRepository: CartRepository,
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

    private val _uiState = MutableStateFlow(CookingDetailState())
    val uiState = _uiState.asStateFlow()

    private val _bottomSheetState = mutableStateOf(
        SheetState(
            skipPartiallyExpanded = false,
            skipHiddenState = true,
            initialValue = SheetValue.PartiallyExpanded
        )
    )

    private val bottomSheetState: State<SheetState> = _bottomSheetState

    private val _bottomSheetScaffoldState = mutableStateOf(
        BottomSheetScaffoldState(
            bottomSheetState = bottomSheetState.value,
            snackbarHostState = SnackbarHostState()
        )
    )

    val bottomSheetScaffoldState: State<BottomSheetScaffoldState> = _bottomSheetScaffoldState

    fun changeServings(newValue: Int) {
        _uiState.update {
            it.copy(servings = newValue)
        }
    }

    fun changeViewMode(newValue: ViewMode) {
        _uiState.update {
            it.copy(viewMode = newValue)
        }
    }

    fun changeDescriptionExpandedState() {
        _uiState.update {
            it.copy(descriptionExpanded = !it.descriptionExpanded)
        }
    }


    fun likeRecipe() {
        viewModelScope.launch {
            recipeRepository.likeRecipe(recipeId, !recipe.first().isLike)
        }
    }

    fun addAllIngredientToCart() {
        viewModelScope.launch {
            recipe.value.let { recipe ->
                recipe.ingredients.forEach {
                    cartRepository.addIngredientToCart(
                        it.ingredientId,
                        (it.amount * _uiState.value.servings / recipe.servings).roundToInt()
                    )
                }
            }

        }
    }

    private val _viewState = MutableStateFlow(CookingDetailTimerState())
    val viewState = _viewState.asStateFlow()

    private var countDown: CountDownTimer? = null

    fun setTimer(millis: Long) {
        countDown?.cancel()
        _viewState.update {
            CookingDetailTimerState(
                timeDuration = millis,
                timerStatus = TimerStatus.INIT,
                remainingTime = millis
            )
        }
    }

    private fun startTimer(millis: Long) {
        _viewState.update {
            it.copy(
                timerStatus = TimerStatus.RUNNING
            )
        }
        countDown = object : CountDownTimer(millis, 10) {
            override fun onTick(millisUntilFinished: Long) {
                _viewState.update {
                    it.copy(
                        remainingTime = millisUntilFinished
                    )
                }
            }

            override fun onFinish() {
                _viewState.update {
                    it.copy(
                        timerStatus = TimerStatus.FINISHED
                    )
                }
            }
        }
        countDown?.start()
    }

    private fun pauseTimer() {
        countDown?.cancel()
        _viewState.update {
            it.copy(
                timerStatus = TimerStatus.PAUSED
            )
        }
    }

    fun buttonSelection() {
        println(_viewState.value)
        when (_viewState.value.timerStatus) {
            TimerStatus.INIT, TimerStatus.FINISHED -> {
                startTimer(_viewState.value.timeDuration)
            }

            TimerStatus.RUNNING -> {
                pauseTimer()
            }

            TimerStatus.PAUSED -> {
                startTimer(_viewState.value.remainingTime)
            }
        }
    }
}

data class CookingDetailState(
    val viewMode: ViewMode = ViewMode.Ingredients,
    val servings: Int = 1,
    val descriptionExpanded: Boolean = false
)

sealed class ViewMode {
    data object Ingredients : ViewMode()
    data object Instructions : ViewMode()
}
