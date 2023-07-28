package com.anbui.recipely.presentation.cooking_detail

data class CookingDetailState(
    val timeDuration: Long = 0,
    val remainingTime: Long = 0,
    val status: Status = Status.INIT,
)

enum class Status {
    INIT, RUNNING, PAUSED, FINISHED
}


sealed class TimerEvent {

}