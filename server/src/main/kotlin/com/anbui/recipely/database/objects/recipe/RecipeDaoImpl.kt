package com.anbui.recipely.database.objects.recipe

import com.anbui.recipely.database.objects.DatabaseSingleton
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class RecipeDaoImpl : RecipeDao {
    private fun resultRowToRecipe(row: ResultRow) = RecipeEntity(
        id = row[Recipe.id],
        description = row[Recipe.description],
        imageUrl = row[Recipe.imageUrl],
        owner = row[Recipe.owner],
        servings = row[Recipe.servings],
        title = row[Recipe.title]
    )

    override suspend fun getAllRecipes(): List<RecipeEntity> {
        return DatabaseSingleton.dbQuery {
            Recipe.selectAll().map(::resultRowToRecipe)
        }
    }

    override suspend fun addRecipe(): RecipeEntity? {
        return DatabaseSingleton.dbQuery {
            val insertStatement = Recipe.insert {
                it[id] = UUID.randomUUID().toString()
                it[title] = UUID.randomUUID().toString().substringBefore("-") + "title"
                it[description] = UUID.randomUUID().toString().substringBefore("-") + "description"
                it[owner] = "admin"
                it[servings] = 4
                it[imageUrl] =
                    "https://www.shutterstock.com/image-photo/red-apple-isolated-on-white-600nw-1727544364.jpg"
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToRecipe)
        }
    }
}