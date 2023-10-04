package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anbui.recipely.data.local.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insertAccount(account: AccountEntity)

    @Query("select * from Account")
    fun getAccounts(): Flow<List<AccountEntity>>
}