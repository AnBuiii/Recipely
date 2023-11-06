package com.anbui.recipely.domain.repository

import kotlinx.coroutines.flow.Flow

interface CurrentPreferences {
    fun getLoggedId(): Flow<String?>

    suspend fun setLoggedId(id: String?)
}
