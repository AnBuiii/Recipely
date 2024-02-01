package com.anbui.recipely.database

import com.anbui.recipely.database.objects.article.DAOFacade
import com.anbui.recipely.database.objects.recipe.RecipeDao
import getPlatform
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.util.*

fun Application.configRouting() {
    val dao: DAOFacade by inject()
    val recipeDao: RecipeDao by inject()
    routing {
        get("/") {
            val platform = getPlatform()
            call.respondText("Recipely Server on ${platform.name}")
        }

        get("/recipe") {
            val respond = recipeDao.getAllRecipes()
            call.respond(respond)
        }

        get("/recipe/add") {
            val respond = recipeDao.addRecipe()
            call.respond(respond ?: "null")
        }

        get("/article") {
            val respond = dao.allArticles()
            call.respond(respond)
        }

        get("/article/add") {
            val article = dao.addNewArticle(UUID.randomUUID().toString(), UUID.randomUUID().toString())
            call.respond(article ?: "null")
        }

    }
}