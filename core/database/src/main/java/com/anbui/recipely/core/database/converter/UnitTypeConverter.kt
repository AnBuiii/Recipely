package com.anbui.recipely.core.database.converter

import androidx.room.TypeConverter
import com.anbui.recipely.core.model.UnitType
import com.anbui.recipely.core.model.UnitType.Companion.toUnitType

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