plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.library.compose)
}

android {
    namespace = "com.anbui.recipely.core.designsystem"
}

dependencies {
    implementation(projects.core.model) // TODO remove
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.constraintlayout.compose)

    api(libs.ui.tooling.preview)
    api(libs.core.ktx)
    api(libs.material3)

    implementation(libs.coil.compose)

}