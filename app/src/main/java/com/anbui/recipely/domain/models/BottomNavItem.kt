package com.anbui.recipely.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    val contentDescription: String? = null,
    val alertCount: Int? = null
)
