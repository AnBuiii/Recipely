plugins {
    alias(libs.plugins.recipely.android.library)
    alias(libs.plugins.recipely.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.anbui.recipely.core.datastore"
}

dependencies {
    api(projects.core.datastoreProto)
    implementation(projects.core.model)

    implementation(libs.androidx.datastore.core)
    implementation(libs.protobuf.kotlin.lite)

}