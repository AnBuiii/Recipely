package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Step")
data class StepEntity(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val id: String,
    @ColumnInfo(name = "time_in_minute") val period: Float,
    @ColumnInfo(name = "recipe_id") val recipeId: String,
    val instruction: String,
    @ColumnInfo(name = "media_url") val mediaUrl: String,
    @ColumnInfo(name = "media_type") val mediaType: String,
    val order: Int
)
