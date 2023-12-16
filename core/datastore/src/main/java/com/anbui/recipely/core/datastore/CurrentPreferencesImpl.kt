package com.anbui.recipely.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentPreferencesImpl @Inject constructor(
    @ApplicationContext val context: Context
) : CurrentPreferences {
    override fun getLoggedId(): Flow<String?> {
        val logged = stringPreferencesKey("logged_id")
        return context.dataStore.data.map { preferences ->
            preferences[logged]
        }
    }

    override suspend fun setLoggedId(id: String?) {
        context.dataStore.edit { settings ->
            if (id != null) {
                val logged = stringPreferencesKey("logged_id")
                settings[logged] = id
            } else {
                settings.clear()
            }
        }
    }

}