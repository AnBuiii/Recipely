package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.domain.models.MediaType.Companion.toMediaType
import com.anbui.recipely.domain.models.Step

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
    fun toStep(): Step {
        return Step(
            id = id,
            order = order,
            instruction = instruction,
            mediaUrl = mediaUrl,
            type = mediaType.toMediaType(),
            period = (period * 1000).toLong()
        )
    }
}
