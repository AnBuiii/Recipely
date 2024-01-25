package com.anbui.recipely.database

import com.anbui.recipely.Meals
import com.anbui.recipely.core.model.NotMediaType
import com.anbui.recipely.core.model.Recipe
import com.anbui.recipely.core.model.Step
import com.anbui.recipely.database.objects.Article
import com.anbui.recipely.database.objects.Articles
import com.anbui.recipely.database.objects.DAOFacade
import getPlatform
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import java.util.UUID

fun Application.configRouting() {
    val dao: DAOFacade by inject()
    routing {
        get("/") {
            val platform = getPlatform()
            call.respondText("Recipely Server on ${platform.name}")
        }

        get("/recipe") {
            val meals = Meals(title = "enim", amount = 2.3f)

            val recipe = Recipe(
                id = "dapibus",
                title = "egestas",
                imageUrl = "https://www.google.com/#q=ridiculus",
                description = "quod",
                isLike = false,
                cookTime = "sapientem",
                servings = 5669,
                totalCalories = 12.13f,
                totalCarb = 14.15f,
                totalProtein = 16.17f,
                totalFat = 18.19f,
                ownerId = "prompta",
                ownerName = "Tamera Beach",
                ownerAvatarUrl = "https://duckduckgo.com/?q=vidisse",
                ownerDescription = "aliquid",
                instructions = listOf(
                    Step(
                        id = "aeque",
                        order = 2504,
                        instruction = "aptent",
                        mediaUrl = null,
                        type = NotMediaType.Image,
                        period = 5154
                    )
                ),
                ingredients = listOf()
            )
            call.respond(recipe)
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