plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.core.testing"
}

dependencies {
    api(libs.junit)
    api(libs.androidx.test.ext.junit)
    api(libs.espresso.core)
}