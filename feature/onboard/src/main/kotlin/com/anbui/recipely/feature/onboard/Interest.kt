package com.anbui.recipely.feature.onboard

import androidx.annotation.DrawableRes

sealed class Interest(val title: String, @DrawableRes val image: Int) {
    data object DiscoverRecipe : Interest("Discover Recipe", R.drawable.img_food_1)
    data object HealthyLiving : Interest("Healthy Living", R.drawable.img_food_2)
    data object EasyFit : Interest("Easy Fit", R.drawable.img_food_3)
    data object Vegetarian : Interest("Vegetarian", R.drawable.img_food_4)
    data object Gluten : Interest("Gluten", R.drawable.img_food_5)
    data object NutFree : Interest("Nut Free", R.drawable.img_food_6)
    data object EasyCooking : Interest("Easy Cooking", R.drawable.img_food_7)
    data object GoodFats : Interest("Good Fats", R.drawable.img_food_8)
}
