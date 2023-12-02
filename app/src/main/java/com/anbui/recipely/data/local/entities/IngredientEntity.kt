package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.domain.models.Ingredient
import com.anbui.recipely.domain.models.UnitType

@Entity(tableName = "Ingredient")
data class IngredientEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,
    val name: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val unit: UnitType,
    val kcal: Float,
    val carb: Float,
    val protein: Float,
    val fat: Float,
    val price: Float
) {
    fun toIngredient(): Ingredient {
        return Ingredient(
            id = id,
            name = name,
            imageUrl = imageUrl,
            unit = unit,
            calories = kcal,
            carb = carb,
            protein = protein,
            fat = fat,
            price = price
        )
    }
}
