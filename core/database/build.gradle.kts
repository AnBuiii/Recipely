plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.hilt)
    alias(libs.plugins.recipely.android.room)
}

android {
    namespace = "com.anbui.recipely.core.database"
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.kotlinx.datetime)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}