package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Recipe",
    foreignKeys = [
//        ForeignKey(
//            entity = AccountEntity::class,
//            parentColumns = arrayOf("_id"),
//            childColumns = arrayOf("owner_id"),
// //            onDelete = ForeignKey.CASCADE,
//        ),
    ]
)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id")
    val id: String,
    val title: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val description: String,
    val servings: Int,
    @ColumnInfo(name = "owner_id") val owner: String
)
