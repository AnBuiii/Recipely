package com.anbui.recipely.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.entities.toAccountEntity
import com.anbui.recipely.dataStore
import com.anbui.recipely.domain.models.Account
import com.anbui.recipely.domain.models.GenderType
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.CurrentPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val currentPreferences: CurrentPreferences,
) : AccountRepository {
    override fun getAllAccount(): Flow<List<Account>> {
        return accountDao.getAccounts().map { accountEntities ->
            accountEntities.map {
                it.toAccount()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCurrentAccount(): Flow<Account> {
        val loggedId = currentPreferences.getLoggedId()
        return loggedId.filterNotNull().flatMapLatest { id -> getAccountById(id) }
    }

    override suspend fun logout() {
        currentPreferences.setLoggedId(null)
    }

    override fun getAccountById(accountId: String): Flow<Account> {
        return accountDao.getAccountById(accountId).map { account ->
            Account(
                id = account.id,
                firstName = account.firstName,
                lastName = account.lastName,
                email = account.email,
                password = account.password,
                bio = account.bio,
                avatarUrl = account.avatarUrl,
                dob = 0,
                gender = GenderType.fromType(account.gender)
            )
        }
    }

    override suspend fun addAccount(account: Account) {
        accountDao.insertAccount(account = account.toAccountEntity())
    }

    override suspend fun login(email: String, password: String): Boolean {
        val account = accountDao.getAccount(email, password)
        account?.let {
            Log.d("???", it.toString())
            currentPreferences.setLoggedId(it.id)
            return true
        }
        return false
    }
}
