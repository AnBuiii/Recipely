package com.anbui.mylibrary.converter

import androidx.room.TypeConverter
import com.anbui.model.UnitType
import com.anbui.model.UnitType.Companion.toUnitType
import java.time.LocalDateTime

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

class UnitTypeConverter {
    @TypeConverter
    fun toUnitType(unitString: String): com.anbui.model.UnitType {
        return unitString.toUnitType()
    }

    @TypeConverter
    fun toUnitString(unit: com.anbui.model.UnitType): String {
        return unit.toString()
    }
}