package com.anbui.recipely.core.testing

import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.model.Account
import com.anbui.recipely.core.model.GenderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class TestAccountRepository @Inject constructor(

) : AccountRepository {

    private val accounts = mutableListOf(
        Account(
            id = "magnis",
            firstName = "Rodrigo Ray",
            lastName = "Kerry Dodson",
            email = "anbui@gmail.com",
            password = "anbui",
            bio = "natoque",
            avatarUrl = "https://duckduckgo.com/?q=idque",
            dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            gender = GenderType.Male,
            street = "possim",
            district = "phasellus",
            province = "sapien"
        )
    )

    override fun getAllAccount(): Flow<List<Account>> {
        return flowOf(accounts)
    }

    override fun getCurrentAccount(): Flow<Account> {
        return flowOf(accounts.first())
    }

    override fun getAccountById(accountId: String): Flow<Account> {
        return flowOf(accounts.first { it.id == accountId })
    }

    override suspend fun updateAccount(account: Account) {
        val index = accounts.indexOfFirst { it.id == account.id }
        accounts[index] = account
    }

    override suspend fun addAccount(account: Account): Boolean {
        if (accounts.any { it.email == account.email }) return false
        return accounts.add(account)
    }

    override suspend fun login(email: String, password: String): Boolean {
        return accounts.any { it.email == email && it.password == password }
    }

    override suspend fun logout() {
        // not do anything
    }
}