import com.anbui.convension.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}
