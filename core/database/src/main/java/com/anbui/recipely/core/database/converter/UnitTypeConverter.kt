package com.anbui.recipely.core.database.converter

import androidx.room.TypeConverter
import com.anbui.model.UnitType.Companion.toUnitType

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