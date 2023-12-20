package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("OrderDetail")
data class OrderIngredientCrossRef(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,
    @ColumnInfo(name = "order_id") val orderId: String,
    @ColumnInfo(name = "ingredient_id") val ingredientId: String,
    val amount: Float
)
