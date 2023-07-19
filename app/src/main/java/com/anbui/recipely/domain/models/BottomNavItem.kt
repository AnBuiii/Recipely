package com.anbui.recipely.domain.models

import androidx.annotation.DrawableRes

data class BottomNavItem(
    val route: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    val contentDescription: String? = null,
    val alertCount: Int? = null
)
