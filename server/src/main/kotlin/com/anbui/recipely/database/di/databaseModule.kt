package com.anbui.recipely.database.di

import com.anbui.recipely.database.objects.DAOFacade
import com.anbui.recipely.database.objects.DAOFacadeImpl
import kotlinx.coroutines.runBlocking
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf

val databaseModule = module {
    single<DAOFacade> {
        DAOFacadeImpl().apply {
            runBlocking {
                if (allArticles().isEmpty()) {
                    addNewArticle("The drive to develop!", "...it's what keeps me going.")
                }
            }
        }
    }
}