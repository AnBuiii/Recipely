package com.anbui.recipely.domain.models

import com.anbui.recipely.domain.models.MediaType.Image

data class Step(
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

val exampleSteps = listOf(
    Step(
        order = 1,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg",
        type = Image,
        period = 212000
    ),
    Step(
        order = 2,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "https://www.eatingwell.com/thmb/m5xUzIOmhWSoXZnY-oZcO9SdArQ=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/article_291139_the-top-10-healthiest-foods-for-kids_-02-4b745e57928c4786a61b47d8ba920058.jpg",
        type = Image,
        period = 15000
    ),
    Step(
        order = 3,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "https://media.istockphoto.com/id/1457889029/photo/group-of-food-with-high-content-of-dietary-fiber-arranged-side-by-side.jpg?b=1&s=612x612&w=0&k=20&c=BON5S0uDJeCe66N9klUEw5xKSGVnFhcL8stPLczQd_8=",
        type = Image,
        period = 120000
    ),
    Step(
        order = 4,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        type = MediaType.Video,
        period = 12000
    ),
    Step(
        order = 5,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg",
        type = Image,
        period = 252000
    ),
    Step(
        order = 12,
        instruction = "Bake the tortilla strips: Coat a baking sheet with nonstick spray. Cut the tortillas into strips, drizzle with oil, and sprinkle with salt and pepper. Toss to coat, and bake at 425 degrees F until golden",
        mediaUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg",
        type = Image,
        period = 312000
    )
)
