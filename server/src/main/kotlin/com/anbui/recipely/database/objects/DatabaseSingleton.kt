package com.anbui.recipely.database.objects

import com.anbui.recipely.database.objects.article.Articles
import com.anbui.recipely.database.objects.recipe.Recipe
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseSingleton {
    fun init() {
        val database =
            Database.connect("jdbc:sqlite:file:./server/src/main/resources/recipely.sqlite", "org.sqlite.JDBC")

        transaction(database) {
            SchemaUtils.create(Articles)
            SchemaUtils.create(Recipe)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}