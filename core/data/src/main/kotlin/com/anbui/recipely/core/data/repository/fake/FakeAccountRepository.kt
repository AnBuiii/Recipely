package com.anbui.recipely.core.data.repository.fake

import com.anbui.recipely.core.data.repository.AccountRepository
import com.anbui.recipely.core.model.Account
import com.anbui.recipely.core.model.GenderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject

class FakeAccountRepository @Inject constructor() : AccountRepository {
    override fun getAllAccount(): Flow<List<Account>> {
        return flow {
            listOf(
                Account(
                    id = "magnis",
                    firstName = "Rodrigo Ray",
                    lastName = "Kerry Dodson",
                    email = "alta.spencer@example.com",
                    password = "sadipscing",
                    bio = "natoque",
                    avatarUrl = "https://duckduckgo.com/?q=idque",
                    dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    gender = GenderType.Male,
                    street = "possim",
                    district = "phasellus",
                    province = "sapien"
                )
            )
        }
    }

    override fun getCurrentAccount(): Flow<Account> {
        return flow {
            Account(
                id = "magnis",
                firstName = "Rodrigo Ray",
                lastName = "Kerry Dodson",
                email = "alta.spencer@example.com",
                password = "sadipscing",
                bio = "natoque",
                avatarUrl = "https://duckduckgo.com/?q=idque",
                dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                gender = GenderType.Male,
                street = "possim",
                district = "phasellus",
                province = "sapien"
            )
        }
    }

    override fun getAccountById(accountId: String): Flow<Account> {
        return flow {
            Account(
                id = accountId,
                firstName = "Rodrigo Ray",
                lastName = "Kerry Dodson",
                email = "alta.spencer@example.com",
                password = "sadipscing",
                bio = "natoque",
                avatarUrl = "https://duckduckgo.com/?q=idque",
                dob = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                gender = GenderType.Male,
                street = "possim",
                district = "phasellus",
                province = "sapien"
            )
        }
    }

    override suspend fun updateAccount(account: Account) {
        // not do anything
    }

    override suspend fun addAccount(account: Account): Boolean {
        // not do anything
        return true
    }

    override suspend fun login(email: String, password: String): Boolean {
        // not do anything
        return true
    }

    override suspend fun logout() {
        // not do anything
    }
}