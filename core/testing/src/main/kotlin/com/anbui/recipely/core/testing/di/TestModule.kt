package com.anbui.recipely.core.testing.di

import com.anbui.recipely.core.data.di.RepositoryModule
import com.anbui.recipely.core.data.impl.AccountRepositoryImpl
import com.anbui.recipely.core.data.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [RepositoryModule::class]
//)
//abstract class RepositoryTestModule {
//    @Binds
//    abstract fun bindAccountRepository(
//        accountRepositoryImpl: AccountRepositoryImpl
//    ): AccountRepository
//}