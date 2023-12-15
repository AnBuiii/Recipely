package com.anbui.model

data class Step(
    val id: String,
    val order: Int,
    val instruction: String,
    val mediaUrl: String?,
    val type: MediaType,
    val period: Long
)

sealed class MediaType(val type: String) {
    object Image : MediaType("image")
    object Video : MediaType("video")

    override fun toString(): String {
        return this.type
    }

    companion object {
        fun String.toMediaType(): MediaType {
            return when (this) {
                Image.type -> Image
                else -> Video
            }
        }
    }
}