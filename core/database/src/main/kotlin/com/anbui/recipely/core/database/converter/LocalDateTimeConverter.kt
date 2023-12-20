package com.anbui.recipely.core.database.converter

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun toLocalDateTime(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime): String {
        return date.toString()
    }
}