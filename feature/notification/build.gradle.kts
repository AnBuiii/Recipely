plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.feature.notification"
}

dependencies {
    implementation(libs.kotlinx.datetime)

}