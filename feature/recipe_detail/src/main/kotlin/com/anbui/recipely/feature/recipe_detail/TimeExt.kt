package com.anbui.recipely.feature.recipe_detail

fun Long.convertSecondsToHMmSs(): String {
    val s = this % 60
    val m = this / 60 % 60
    val h = this / (60 * 60) % 24
    return if (h == 0L) {
        String.format("%02d:%02d", m, s)
    } else {
        String.format("%d:%02d:%02d", h, m, s)
    }
}
