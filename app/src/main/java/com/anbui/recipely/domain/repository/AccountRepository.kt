package com.anbui.recipely.domain.repository

import com.anbui.recipely.domain.models.Account
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun getAllAccount(): Flow<List<Account>>

    suspend fun addAccount(account: Account)
}
