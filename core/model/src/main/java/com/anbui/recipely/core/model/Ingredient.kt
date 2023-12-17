package com.anbui.recipely.core.model

data class Ingredient(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val unit: UnitType,
    val calories: Float,
    val carb: Float,
    val protein: Float,
    val fat: Float,
    val price: Float = 0.23f
)