package com.anbui.recipely.domain.models

import androidx.annotation.DrawableRes

data class InterestItem(
    @DrawableRes val image: Int,
    val interest: String
)
