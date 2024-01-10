pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Recipely"
include(":composeApp")
include(":core:model")
include(":core:database")
include(":core:designsystem")
include(":core:datastore")
include(":core:datastore-proto")
include(":core:data")
include(":core:testing")
include(":feature:search")
include(":feature:recipe_detail")
include(":feature:onboard")
include(":feature:notification")
include(":feature:create_recipe")
include(":feature:cart")
include(":feature:account")
include(":feature:ingredient_detect")
include(":shared")
include(":server")
