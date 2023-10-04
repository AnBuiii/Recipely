package com.anbui.recipely.presentation.recipe.cooking_detail

data class CookingDetailTimerState(
    val timeDuration: Long = 0,
    val remainingTime: Long = 0,
    val timerStatus: TimerStatus = TimerStatus.INIT,
)

enum class TimerStatus {
    INIT, RUNNING, PAUSED, FINISHED
}



sealed class ViewMode {
    object Ingredients : ViewMode()
    object Instructions : ViewMode()
}
