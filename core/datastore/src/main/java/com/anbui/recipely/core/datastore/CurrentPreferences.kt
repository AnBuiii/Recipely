package com.anbui.recipely.core.datastore

import kotlinx.coroutines.flow.Flow

interface CurrentPreferences {
    fun getLoggedId(): Flow<String?>

    suspend fun setLoggedId(id: String?)
}
