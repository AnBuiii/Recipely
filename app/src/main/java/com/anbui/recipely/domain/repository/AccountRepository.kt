package com.anbui.recipely.domain.repository

import com.anbui.recipely.domain.models.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccount(): Flow<List<Account>>

    fun getCurrentAccount(): Flow<Account>

    fun getAccountById(accountId: String): Flow<Account>

    suspend fun addAccount(account: Account)

    suspend fun login(email: String, password: String): Boolean


}
