plugins {
    alias(libs.plugins.recipely.android.feature)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.feature.cart"
}

dependencies {
    api(libs.ui.tooling.preview)
    api(libs.core.ktx)
    api(libs.material3)

    implementation(libs.ui.util)
    implementation(libs.activity.compose)

    implementation(libs.kotlinx.datetime)
    implementation(libs.coil.compose)
}
