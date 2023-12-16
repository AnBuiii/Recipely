@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.recipely.android.hilt)
}

android {
    namespace = "com.anbui.recipely.core.datastore"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.datastore.preferences)
}