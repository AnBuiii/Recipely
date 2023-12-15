package com.anbui.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.model.MediaType.Companion.toMediaType
import com.anbui.model.Step

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
) {
    fun toStep(): com.anbui.model.Step {
        return com.anbui.model.Step(
            id = id,
            order = order,
            instruction = instruction,
            mediaUrl = mediaUrl,
            type = mediaType.toMediaType(),
            period = (period * 1000).toLong()
        )
    }
}
