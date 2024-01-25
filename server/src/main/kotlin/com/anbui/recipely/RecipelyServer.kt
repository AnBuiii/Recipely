package com.anbui.recipely

import com.anbui.recipely.database.configKoin
import com.anbui.recipely.database.configRouting
import com.anbui.recipely.database.configSerialization
import com.anbui.recipely.database.objects.DatabaseSingleton
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.serialization.Serializable

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        DatabaseSingleton.init()
        configKoin()
        configSerialization()
        configRouting()


    }.start(wait = true)
}

@Serializable
data class Meals(
    val title: String, val amount: Float
)