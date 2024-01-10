plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

group = "com.anbui.recipely"
version = "1.0.0"

application {
    mainClass.set("com.anbui.recipely.RecipelyServer")
//    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
//    implementation(projects.shared)
    implementation(libs.logback)
    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Test
}
