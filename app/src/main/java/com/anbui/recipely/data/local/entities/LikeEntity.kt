package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Like")
data class LikeEntity(
    @PrimaryKey()
    @ColumnInfo("_id")
    val id: String,
    @ColumnInfo("recipe_id") val recipeId: String,
    @ColumnInfo("account_id") val accountId: String
)
