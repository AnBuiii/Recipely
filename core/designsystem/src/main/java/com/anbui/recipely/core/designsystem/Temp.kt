package com.anbui.recipely.core.designsystem

import android.content.res.Resources
import android.text.format.DateUtils
import android.util.TypedValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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

fun Dp.toPx(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.value,
        Resources.getSystem().displayMetrics
    )
}

fun Float.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}