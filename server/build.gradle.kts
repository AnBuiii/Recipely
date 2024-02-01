plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
    application
}

group = "com.anbui.recipely"
version = "1.0.0"

application {
    mainClass.set("com.anbui.recipely.RecipelyServer")
    println("Dio.ktor.development=${extra["development"] ?: "false"}")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    // model
    implementation(projects.core.model)

    implementation(projects.shared)
    implementation(libs.logback)

    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Exposed
    val exposed_version = "0.46.0"
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

    // h2
    val h2_version = "2.1.214"
    implementation("com.h2database:h2:$h2_version")

    // Koin
    val koin_version = "3.6.0-wasm-alpha1"
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")

    // Test
}
