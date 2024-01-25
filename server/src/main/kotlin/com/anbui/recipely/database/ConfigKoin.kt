package com.anbui.recipely.database

import com.anbui.recipely.database.di.databaseModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configKoin() {
    install(Koin) {
        modules(databaseModule)
    }
}