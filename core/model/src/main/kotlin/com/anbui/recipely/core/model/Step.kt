package com.anbui.recipely.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Step(
    val id: String,
    val order: Int,
    val instruction: String,
    val mediaUrl: String?,
    val type: NotMediaType,
    val period: Long
)

enum class NotMediaType {
    Image, Video,
}

fun String.toMediaType(): NotMediaType{
    return NotMediaType.valueOf(this)
}
