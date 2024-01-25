package com.anbui.recipely.database.objects.recipe

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class RecipeEntity(
    val id: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val servings: Int,
    val owner: String
)

object Recipe : Table() {
    val id = varchar("_id",128)
    val title = varchar("title", 128)
    val imageUrl = varchar("image_url", 128)
    val description = varchar("description", 1024)
    val servings = integer("servings")
    val owner = varchar("owner_id", 1024)

    override val primaryKey = PrimaryKey(id)
}
