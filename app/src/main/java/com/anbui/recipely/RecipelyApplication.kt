package com.anbui.recipely

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RecipelyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
