package com.anbui.recipely.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class RecipelyPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val loggedId = userPreferences.data.map {
        it.loggedId
    }

    suspend fun setLoggedId(id: String) {
        try {
            userPreferences.updateData {
                it.copy {
                    loggedId = id
                }
            }
        } catch (ioException: IOException) {
            Log.e("NiaPreferences", "Failed to update user preferences", ioException)
        }
    }


}