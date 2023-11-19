package com.anbui.recipely.data.local.entities.converter

import androidx.room.TypeConverter
import com.anbui.recipely.domain.models.UnitType
import com.anbui.recipely.domain.models.UnitType.Companion.toUnitType
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
    fun toUnitType(unitString: String): UnitType {
        return unitString.toUnitType()
    }

    @TypeConverter
    fun toUnitString(unit: UnitType): String {
        return unit.toString()
    }
}