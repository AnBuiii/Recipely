package com.anbui.recipely.domain.models

sealed class GenderType(val type: String) {
    data object Male : GenderType("male")
    data object Female : GenderType("female")

    companion object {
        fun fromType(type: String): GenderType {
            return when(type) {
                "male" -> Male
                else -> Female
            }
        }
        fun GenderType.getType(): String{
            return when(this){
                Male -> Male.type
                Female -> Female.type
            }
        }
    }

}
