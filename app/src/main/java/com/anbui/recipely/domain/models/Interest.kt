package com.anbui.recipely.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.res.stringResource
import com.anbui.recipely.R

sealed class Interest(val title: String, @DrawableRes val image : Int){
    object DiscoverRecipe: Interest("Discover Recipe", R.drawable.img_food_1)
    object HealthyLiving: Interest("Healthy Living", R.drawable.img_food_2)
    object EasyFit: Interest("Easy Fit", R.drawable.img_food_3)
    object Vegetarian: Interest("Vegetarian", R.drawable.img_food_4)
    object Gluten: Interest("Gluten", R.drawable.img_food_5)
    object NutFree: Interest("Nut Free", R.drawable.img_food_6)
    object EasyCooking: Interest("Easy Cooking", R.drawable.img_food_7)
    object GoodFats: Interest("Good Fats", R.drawable.img_food_8)
}
