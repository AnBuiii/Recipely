import com.anbui.convention.RecipelyBuildType

plugins {
    alias(libs.plugins.recipely.android.application)
    alias(libs.plugins.recipely.android.application.compose)
    alias(libs.plugins.recipely.android.hilt)
    alias(libs.plugins.recipely.android.application.flavors)
}

android {
    namespace = "com.anbui.recipely"

    defaultConfig {
        applicationId = "com.anbui.recipely" //
        versionCode = 1  //
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" //
        vectorDrawables {//
            useSupportLibrary = true //
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = RecipelyBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = false
            applicationIdSuffix = RecipelyBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.getByName("debug")
            // Ensure Baseline Profile is fresh for release builds.
//            baselineProfile.automaticGenerationDuringBuild = true
        }
    } //
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.datastore)
    implementation(projects.core.data)

    implementation(projects.feature.onboard)
    implementation(projects.feature.notification)
    implementation(projects.feature.search)
    implementation(projects.feature.recipeDetail)
    implementation(projects.feature.createRecipe)
    implementation(projects.feature.cart)
    implementation(projects.feature.account)
    implementation(projects.feature.ingredientDetect)

    implementation(projects.shared)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.ui.util)


    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Dagger - Hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)

    // Coil
    implementation(libs.coil.compose)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    // Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    implementation(libs.kotlinx.datetime)

    // Testing

//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.espresso.core)

//    androidTestImplementation(libs.ui.test.junit4)
//    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

//    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(kotlin("test"))

    testImplementation(projects.core.testing)
    testImplementation(kotlin("test"))




}
