package com.anbui.recipely.feature.notification

import android.text.format.DateUtils
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import java.text.ParseException

fun LocalDateTime.timeAgo(minResolution: Long): String {
    return try {
        val then = this.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
        val now = System.currentTimeMillis()
        val ago = DateUtils.getRelativeTimeSpanString(
            then,
            now,
            minResolution,
        )
        ago.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
        "fail"
    }
}
