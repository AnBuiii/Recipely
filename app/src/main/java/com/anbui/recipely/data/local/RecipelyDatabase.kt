package com.anbui.recipely.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anbui.recipely.data.local.account.AccountDao
import com.anbui.recipely.data.local.account.AccountEntity


@Database(
    version = 1,
    entities = [AccountEntity::class]
)
abstract class RecipelyDatabase: RoomDatabase() {
    abstract val accountDao: AccountDao
}