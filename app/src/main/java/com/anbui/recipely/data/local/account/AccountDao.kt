package com.anbui.recipely.data.local.account

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: AccountEntity)
}