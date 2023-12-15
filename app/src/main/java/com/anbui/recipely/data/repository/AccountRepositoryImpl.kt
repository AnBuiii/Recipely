package com.anbui.recipely.data.repository

import android.util.Log
import com.anbui.database.AccountDao
import com.anbui.database.toAccountEntity
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.CurrentPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.util.UUID
import javax.inject.Inject


class AccountRepositoryImpl @Inject constructor(
    private val accountDao: com.anbui.database.AccountDao,
    private val currentPreferences: CurrentPreferences,
) : AccountRepository {
    override fun getAllAccount(): Flow<List<com.anbui.model.Account>> {
        return accountDao.getAccounts().map { accountEntities ->
            accountEntities.map {
                it.toAccount()
            }
        }
    }

    override fun getCurrentAccount(): Flow<com.anbui.model.Account> {
        val loggedId = currentPreferences.getLoggedId()
        return loggedId.filterNotNull().transform { id ->
            emit(getAccountById(id).first())
        }
    }

    override suspend fun logout() {
        currentPreferences.setLoggedId(null)
    }

    override fun getAccountById(accountId: String): Flow<com.anbui.model.Account> {
        return accountDao.getAccountById(accountId).map { account ->
            com.anbui.model.Account(
                id = account.id,
                firstName = account.firstName,
                lastName = account.lastName,
                email = account.email,
                password = account.password,
                bio = account.bio,
                avatarUrl = account.avatarUrl,
                dob = account.dob,
                gender = com.anbui.model.GenderType.fromType(account.gender),
                street = account.street,
                district = account.district,
                province = account.province,
            )
        }
    }

    override suspend fun updateCurrentAccount(account: com.anbui.model.Account) {
        accountDao.updateAccount(account.toAccountEntity())
    }

    override suspend fun addAccount(account: com.anbui.model.Account): Boolean {
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
            currentPreferences.setLoggedId(it.id)
            return true
        }
        return false
    }
}
