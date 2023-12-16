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

    implementation(libs.ui.tooling.preview)
    implementation(libs.core.ktx)
    implementation(libs.material3)
    implementation(libs.coil.compose)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}