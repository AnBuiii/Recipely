package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recent")
data class RecentEntity(
    @PrimaryKey()
    @ColumnInfo("_id")
    val id: String,
    @ColumnInfo("recipe_id") val recipeId: String,
    @ColumnInfo("account_id") val accountId: String
)
