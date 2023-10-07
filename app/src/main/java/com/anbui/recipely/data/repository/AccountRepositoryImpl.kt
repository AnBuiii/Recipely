package com.anbui.recipely.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.entities.toAccountEntity
import com.anbui.recipely.dataStore
import com.anbui.recipely.domain.models.Account
import com.anbui.recipely.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountRepositoryImpl(
    private val accountDao: AccountDao,
    private val context: Context
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

    override suspend fun login(email: String, password: String): Boolean {
        val account = accountDao.getAccount(email, password)
        account?.let {
            context.dataStore.edit { settings ->
                val logged = stringPreferencesKey("logged_id")
                settings[logged] = it.id
            }
            return true
        }
        return false
    }
}
