import android.os.Build

class AndroidPlatform: Platform {
    override val name: String
        get() = "Android ${Build.VERSION.SDK_INT}"
}
actual fun getPlatform(): Platform = AndroidPlatform()