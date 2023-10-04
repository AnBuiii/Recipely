package com.anbui.recipely.presentation.recipe.cooking_detail

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookingDetailViewModel @Inject constructor() : ViewModel() {
    private val _viewState = mutableStateOf(CookingDetailTimerState())
    val viewState: State<CookingDetailTimerState> = _viewState

    private var countDown: CountDownTimer? = null

    fun setTimer(millis: Long) {
        countDown?.cancel()
        _viewState.value = CookingDetailTimerState(
            timeDuration = millis,
            timerStatus = TimerStatus.INIT,
            remainingTime = millis
        )
    }

    private fun startTimer(millis: Long) {
        _viewState.value = _viewState.value.copy(
            timerStatus = TimerStatus.RUNNING
        )
        countDown = object : CountDownTimer(millis, 10) {
            override fun onTick(millisUntilFinished: Long) {
                _viewState.value = _viewState.value.copy(
                    remainingTime = millisUntilFinished,
                )
            }

            override fun onFinish() {
                _viewState.value = _viewState.value.copy(
                    timerStatus = TimerStatus.FINISHED,
                )
            }

        }
        countDown?.start()
    }

    private fun pauseTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value.copy(
            timerStatus = TimerStatus.PAUSED,
        )
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

    private val _viewMode = mutableStateOf<ViewMode>(ViewMode.Ingredients)
    val viewMode: State<ViewMode> = _viewMode

    fun changeViewMode(newValue: ViewMode) {
        _viewMode.value = newValue
    }
}