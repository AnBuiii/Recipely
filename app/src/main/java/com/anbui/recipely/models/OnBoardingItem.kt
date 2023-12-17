package com.anbui.recipely.models

import androidx.annotation.DrawableRes

data class OnBoardingItem(
    val title: String,
    val subtitle: String,
    @DrawableRes val img: Int
)
