plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.library.compose)
    alias(libs.plugins.recipely.android.hilt)
}

android {
    namespace = "com.anbui.recipely.core.testing"
}

dependencies {



    api(libs.junit)
    api(libs.ui.test.junit4)
    api("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0-RC2")
    api(libs.androidx.test.ext.junit)
    api(libs.espresso.core)
    api(libs.hilt.android.testing)

    implementation(libs.kotlinx.datetime)
    implementation(projects.core.data)
    implementation(projects.core.model)

}