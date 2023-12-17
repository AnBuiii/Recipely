package com.anbui.recipely.core.model

sealed class GenderType(val type: String) {
    data object Male : GenderType("male")
    data object Female : GenderType("female")

    companion object {
        fun fromType(type: String): GenderType {
            return when (type) {
                "male" -> Male
                else -> Female
            }
        }

        fun GenderType.getType(): String {
            return this.type
        }
    }
}
