package com.anbui.recipely.presentation.recipe.cooking_detail

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.core.data.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CookingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    recipeRepository: RecipeRepository
) : ViewModel() {
    private val _viewState = MutableStateFlow(CookingDetailTimerState())
    val viewState = _viewState.asStateFlow()

    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])
    val recipe = recipeRepository.getRecipesById(recipeId)
        .onEach {
            changeServings(it.servings)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            recipeRepository.getDummyRecipe()
        )

    private var countDown: CountDownTimer? = null

    private fun changeServings(value: Int) {
        _viewState.update { it.copy(serving = value) }
    }

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

    private val _viewMode = MutableStateFlow<ViewMode>(ViewMode.Ingredients)
    val viewMode = _viewMode.asStateFlow()

    fun changeViewMode(newValue: ViewMode) {
        _viewMode.update { newValue }
    }
}
