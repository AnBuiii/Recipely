package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.anbui.recipely.data.local.entities.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insertAccount(account: AccountEntity)

    @Query("select * from Account where email = :email and password = :password")
    suspend fun getAccount(email: String, password: String): AccountEntity?

    @Query("select * from Account where _id = :accountId")
    fun getAccountById(accountId: String): Flow<AccountEntity>

    @Query("select * from Account")
    fun getAccounts(): Flow<List<AccountEntity>>

    @Update
    suspend fun updateAccount(accountEntity: AccountEntity)

}
