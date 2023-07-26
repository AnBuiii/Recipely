package com.anbui.recipely.domain.models

sealed class GenderType(val type: Int) {
    object Male : GenderType(0)
    object Female : GenderType(1)
}
