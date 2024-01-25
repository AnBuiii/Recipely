package com.anbui.recipely.database

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.nullable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

fun Application.configSerialization() {
    install(ContentNegotiation) {
        json(
            Json {
//                useArrayPolymorphism = true
                prettyPrint = true
                ignoreUnknownKeys = true
                isLenient = true
//                serializersModule = SerializersModule {
//                    polymorphic(Any::class) {
//                        subclass(String::class, String.serializer())
//                        subclass(Boolean::class, Boolean.serializer())
//                        subclass(List::class, ListSerializer(PolymorphicSerializer(Any::class).nullable))
//                    }
//                }
            }
        )
    }
}