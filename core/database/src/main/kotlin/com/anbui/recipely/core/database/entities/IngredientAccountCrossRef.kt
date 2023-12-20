package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class IngredientAccountCrossRef(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,
    @ColumnInfo(name = "ingredient_id") val ingredientId: String,
    @ColumnInfo(name = "account_id") val accountId: String,
    val amount: Float
)
