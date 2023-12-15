package com.anbui.recipely.domain.repository

import com.anbui.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccount(): Flow<List<com.anbui.model.Account>>

    fun getCurrentAccount(): Flow<com.anbui.model.Account>

    fun getAccountById(accountId: String): Flow<com.anbui.model.Account>

    suspend fun updateCurrentAccount(account: com.anbui.model.Account)

    suspend fun addAccount(account: com.anbui.model.Account): Boolean

    suspend fun login(email: String, password: String): Boolean

    suspend fun logout()
}
