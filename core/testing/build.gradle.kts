plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.library.compose)
// this require compose compiler version from application.compose
    alias(libs.plugins.recipely.android.hilt)
}

android {
    namespace = "com.anbui.recipely.core.testing"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)

    api(libs.kotlinx.datetime)

    api(libs.junit)
    api(libs.ui.test.junit4)
//    debugApi(libs.ui.test.manifest)
    api(libs.androidx.test.ext.junit)
    api(libs.espresso.core)
    api(libs.hilt.android.testing)
}