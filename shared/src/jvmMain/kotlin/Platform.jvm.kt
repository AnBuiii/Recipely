import jdk.jfr.internal.JVM

class JVMPlatform: Platform {
    override val name: String
        get() = "Java ${System.getProperty("java.version")}"
}
actual fun getPlatform(): Platform = JVMPlatform()