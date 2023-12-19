package com.anbui.recipely.core.data.repository

import com.anbui.recipely.core.model.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccount(): Flow<List<Account>>

    fun getCurrentAccount(): Flow<Account>

    fun getAccountById(accountId: String): Flow<Account>

    suspend fun updateAccount(account: Account)

    suspend fun addAccount(account: Account): Boolean

    suspend fun login(email: String, password: String): Boolean

    suspend fun logout()
}