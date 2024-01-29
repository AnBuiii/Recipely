plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.feature.account"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.compose)
}
