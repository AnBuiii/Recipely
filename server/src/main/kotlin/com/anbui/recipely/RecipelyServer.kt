package com.anbui.recipely

import getPlatform
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0"){
        routing {
            get("/"){
                val platform = getPlatform()
                call.respondText("Recipely Server on ${platform.name}")
            }
        }
//        configureSerialization()
    }
        .start(wait = true)

}