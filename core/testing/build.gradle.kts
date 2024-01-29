plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.library.compose)
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
    api(libs.androidx.test.ext.junit)
    api(libs.espresso.core)
    api(libs.hilt.android.testing)
}