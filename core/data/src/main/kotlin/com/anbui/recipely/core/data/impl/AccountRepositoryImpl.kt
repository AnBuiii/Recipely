package com.anbui.recipely.core.data.impl

import android.util.Log
import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.database.dao.AccountDao
import com.anbui.recipely.core.database.entities.toAccountEntity
import com.anbui.recipely.core.datastore.RecipelyPreferencesDataSource
import com.anbui.recipely.core.model.Account
import com.anbui.recipely.core.model.GenderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao,
    private val preferencesDataSource: RecipelyPreferencesDataSource
) : AccountRepository {
    override fun getAllAccount(): Flow<List<Account>> {
        return accountDao.getAccounts().map { accountEntities ->
            accountEntities.map {
                it.toAccount()
            }
        }
    }

    override fun getCurrentAccount(): Flow<Account> {
        val loggedId = preferencesDataSource.loggedId.filterNot { it.isNullOrEmpty() }
        return loggedId.transform { id ->
            emit(getAccountById(id).first())
        }
    }

    override suspend fun logout() {
        preferencesDataSource.setLoggedId("")
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
                dob = account.dob,
                gender = GenderType.fromType(account.gender),
                street = account.street,
                district = account.district,
                province = account.province,
            )
        }
    }

    override suspend fun updateAccount(account: Account) {
        accountDao.updateAccount(account.toAccountEntity())
    }

    override suspend fun addAccount(account: Account): Boolean {
        return try {
            if (accountDao.getAccountFromEmail(account.email) != null) {
                false
            } else {
                accountDao.insertAccount(
                    account = account.copy(id = UUID.randomUUID().toString()).toAccountEntity()
                )
                true
            }
        } catch (_: Exception) {
            false
        }

    }

    override suspend fun login(email: String, password: String): Boolean {
        val account = accountDao.getAccount(email, password)
        account?.let {
            Log.d("???", it.toString())
            preferencesDataSource.setLoggedId(it.id)
            return true
        }
        return false
    }
}
