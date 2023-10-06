package com.anbui.recipely.data.repository

import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.entities.toAccountEntity
import com.anbui.recipely.domain.models.Account
import com.anbui.recipely.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val accountDao: AccountDao
) : AccountRepository {
    override fun getAllAccount(): Flow<List<Account>> {
        return accountDao.getAccounts().map { accountEntities ->
            accountEntities.map {
                it.toAccount()
            }
        }
    }

    override suspend fun addAccount(account: Account) {
        accountDao.insertAccount(account = account.toAccountEntity())
    }
}
