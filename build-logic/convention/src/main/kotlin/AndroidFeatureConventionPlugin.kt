import com.anbui.convension.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("recipely.android.library")
                apply("recipely.android.hilt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner =
                        "com.anbui.recipely.core.testing.RecipelyTestRunner"
                }
            }

            dependencies {
                add("implementation", project(":core:model"))
//                add("implementation", project(":core:ui"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:testing"))
//                add("implementation", project(":core:common"))
//                add("implementation", project(":core:domain"))
//                add("implementation", project(":core:analytics"))

                add("testImplementation", kotlin("test"))
                add("testImplementation", project(":core:testing"))
                add("androidTestImplementation", kotlin("test"))
                add("androidTestImplementation", project(":core:testing"))

//                add("implementation", libs.findLibrary("coil.kt").get())
//                add("implementation", libs.findLibrary("coil.kt.compose").get())

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())

//                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}
