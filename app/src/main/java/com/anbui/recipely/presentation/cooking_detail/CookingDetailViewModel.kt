package com.anbui.recipely.presentation.cooking_detail

import android.os.CountDownTimer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CookingDetailViewModel @Inject constructor() : ViewModel() {
    private val _viewState = mutableStateOf(CookingDetailState())
    val viewState: State<CookingDetailState> = _viewState

    var countDown: CountDownTimer? = null

    fun setTimer(millis: Long) {
        countDown?.cancel()
        _viewState.value = _viewState.value.copy(
            timeDuration = millis,
            status = Status.INIT
        )
    }
    fun startTimer(millis: Long) {
        _viewState.value = _viewState.value.copy(
            status = Status.RUNNING
        )
        countDown = object : CountDownTimer(millis, 10) {
            override fun onTick(millisUntilFinished: Long) {
                _viewState.value =  _viewState.value.copy(
                    remainingTime = millisUntilFinished,
                )
            }

            override fun onFinish() {
                _viewState.value = _viewState.value.copy(
//                    timeDuration = 0,
                    status = Status.FINISHED,
                )
            }

        }
        countDown?.start()
    }

    fun pauseTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value.copy(
            status = Status.PAUSED,
        )
    }


    fun buttonSelection() {
        println(_viewState.value)
       when(_viewState.value.status){
           Status.INIT, Status.FINISHED -> {
               startTimer(_viewState.value.timeDuration)
           }
           Status.RUNNING -> {
               pauseTimer()
           }
           Status.PAUSED -> {
               startTimer(_viewState.value.remainingTime)
           }
       }

    }
}