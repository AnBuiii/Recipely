package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.core.model.Step
import com.anbui.recipely.core.model.toMediaType

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

fun StepEntity.toStep(): Step {
    return Step(
        id = id,
        order = order,
        instruction = instruction,
        mediaUrl = mediaUrl,
        type = mediaType.toMediaType(),
        period = (period * 1000).toLong()
    )
}


