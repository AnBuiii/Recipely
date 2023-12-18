package com.anbui.recipely.feature.recipe_detail

data class CookingDetailTimerState(
    val timeDuration: Long = 0,
    val remainingTime: Long = 0,
    val timerStatus: TimerStatus = TimerStatus.INIT,
)

enum class TimerStatus {
    INIT, RUNNING, PAUSED, FINISHED
}

